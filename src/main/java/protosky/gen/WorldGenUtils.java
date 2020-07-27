package protosky.gen;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.network.SpawnLocating;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.PackedIntegerArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
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

        s.place(new ChunkRegion(world, ImmutableList.of(chunk)), chunk.getPos().toBlockPos(0,64,0), new StructurePlacementData().setUpdateNeighbors(true), new Random());
        world.setSpawnPos(chunk.getPos().toBlockPos(s.getSize().getX() / 2, 64 + s.getSize().getY() + 1, s.getSize().getZ() / 2));
    }
}
