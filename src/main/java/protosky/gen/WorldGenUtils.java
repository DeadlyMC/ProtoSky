package protosky.gen;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.PackedIntegerArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.chunk.WorldChunk;
import protosky.mixins.ProtoChunkAccessor;

import java.util.*;

public class WorldGenUtils
{
    public static void deleteBlocks(ProtoChunk chunk, ServerWorld world)
    {
        ChunkSection[] sections = chunk.getSectionArray();
        Arrays.fill(sections, WorldChunk.EMPTY_SECTION);
        for (BlockPos bePos : chunk.getBlockEntityPositions())
        {
            chunk.removeBlockEntity(bePos);
        }
        ((ProtoChunkAccessor) chunk).getLightSources().clear();
        long[] emptyHeightmap = new PackedIntegerArray(9, 256).getStorage();
        for (Map.Entry<Heightmap.Type, Heightmap> heightmapEntry : chunk.getHeightmaps())
        {
            heightmapEntry.getValue().setTo(emptyHeightmap);
        }
        StructureHelper.processStronghold(chunk, world);

        if (world.getRegistryKey() == World.END)
            StructureHelper.generatePillars(chunk, world, world.getEnderDragonFight());
    }


    public static void clearEntities(ProtoChunk chunk, WorldAccess world)
    {
        // erase entities
        if (world.getWorld().getRegistryKey() != World.END)
        {
            chunk.getEntities().clear();
        }
        else
        {
            chunk.getEntities().removeIf(tag ->
            {
                String id = tag.getString("id");
                return !id.equals("minecraft:end_crystal") && !id.equals("minecraft:shulker") && !id.equals("minecraft:item_frame");
            });
        }
    }

    public static void genSpawnPlatform(Chunk chunk, ServerWorld world) {
        StructureManager man = world.getStructureManager();
        Structure s = null;

        // Get structure for this dimension
        if (world.getRegistryKey() == World.OVERWORLD) {
            s = man.getStructure(new Identifier("protosky:spawn_overworld"));
        } else if (world.getRegistryKey() == World.NETHER) {
            s = man.getStructure(new Identifier("protosky:spawn_nether"));
        }
        if (s == null) return;
        CompoundTag t = new CompoundTag();

        // s.place would call world.getChunk, causing it to hang.
        // Hence, I've reimplemented spawning a structure.
        // The limitation here is that the structure is limited to 16 blocks,
        // and assumes it centers around (8, 0, 8).
        // Other limitations for this implementation are:
        // - No support for BlockEntities
        // - No support for Entities
        // Additionally, this platform is spawned at Y=64 with no way to configure this.

        s.toTag(t);

        // Collect the palette
        Map<Integer, BlockState> map = new HashMap<>();
        ListTag palette = t.getList("palette", 10);
        for (int i = 0; i < palette.size(); i++) {
            CompoundTag e = palette.getCompound(i);
            map.put(i, NbtHelper.toBlockState(e));
        }
        BlockPos.Mutable relStart = chunk.getPos().getCenterBlockPos().mutableCopy();
        relStart.setY(64);

        // Place the blocks in the chunk
        BlockPos highest = BlockPos.ORIGIN;
        ListTag blocks = t.getList("blocks", 10);
        for (int i = 0; i < blocks.size(); i++) {
            CompoundTag b = blocks.getCompound(i);
            BlockState st = map.get(b.getInt("state"));
            ListTag t2 = b.getList("pos", 3);
            BlockPos p = relStart.add(new BlockPos(t2.getInt(0), t2.getInt(1), t2.getInt(2)));
            if (p.getY() > highest.getY()) {
                highest = p;
            }
            chunk.setBlockState(p, st, false);
        }

        // Because of the way we spawn the structure, it's common to fall into the void next to it.
        // As solution, we simply set the world spawn on top of said structure.
        world.setSpawnPos(highest.up());
    }
}
