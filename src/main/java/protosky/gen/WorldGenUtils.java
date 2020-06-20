package protosky.gen;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.PackedIntegerArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
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

        Heightmap.populateHeightmaps(chunk, EnumSet.allOf(Heightmap.Type.class));
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
}
