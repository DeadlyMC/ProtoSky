package pt.skyblock.gen;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerLightingProvider;
import net.minecraft.util.PackedIntegerArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.*;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.TheEndDimension;
import net.minecraft.world.gen.chunk.*;
import net.minecraft.world.level.LevelGeneratorOptions;
import net.minecraft.world.level.LevelGeneratorType;
import pt.skyblock.mixins.ProtoChunkAccessor;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class WorldGenUtils
{
    public static LevelGeneratorType LEVEL_GENERATOR_TYPE;
    
    public static LevelGeneratorOptions createOverworldChunkGenerator(LevelGeneratorType generator, Dynamic<?> dynamic)
    {
        ChunkGeneratorType<OverworldChunkGeneratorConfig, OverworldChunkGenerator> chunkGeneratorType = ChunkGeneratorType.SURFACE;
        BiomeSourceType<VanillaLayeredBiomeSourceConfig, VanillaLayeredBiomeSource> biomeSourceType = BiomeSourceType.VANILLA_LAYERED;
        OverworldChunkGeneratorConfig chunkGeneratorConfig = chunkGeneratorType.createConfig();
        return new LevelGeneratorOptions(generator, dynamic, (iWorld) ->
        {
            VanillaLayeredBiomeSourceConfig biomeSourceConfig = biomeSourceType.getConfig(iWorld.getSeed()).setGeneratorType(generator).setGeneratorConfig(chunkGeneratorConfig);
            return new SkyBlockOverworldGenerator(iWorld, biomeSourceType.applyConfig(biomeSourceConfig), chunkGeneratorConfig);
        });
    }
    
    public static ChunkGenerator<? extends ChunkGeneratorConfig> createNetherChunkGenerator(World world)
    {
        CavesChunkGeneratorConfig config = ChunkGeneratorType.CAVES.createConfig();
        config.setDefaultBlock(Blocks.NETHERRACK.getDefaultState());
        config.setDefaultFluid(Blocks.LAVA.getDefaultState());
        MultiNoiseBiomeSourceConfig multiNoiseBiomeSourceConfig = BiomeSourceType.MULTI_NOISE.getConfig(world.getSeed()).withBiomes(ImmutableSet.of(Biomes.NETHER_WASTES, Biomes.SOUL_SAND_VALLEY, Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST));
        return new SkyBlockCavesGenerator(world, BiomeSourceType.MULTI_NOISE.applyConfig(multiNoiseBiomeSourceConfig), config);
    }
    
    public static ChunkGenerator<? extends ChunkGeneratorConfig> createEndChunkGenerator(World world)
    {
        FloatingIslandsChunkGeneratorConfig config = ChunkGeneratorType.FLOATING_ISLANDS.createConfig();
        config.setDefaultBlock(Blocks.END_STONE.getDefaultState());
        config.setDefaultFluid(Blocks.AIR.getDefaultState());
        config.withCenter(TheEndDimension.SPAWN_POINT);
        return new SkyBlockFloatingIslandsGenerator(world, BiomeSourceType.THE_END.applyConfig((BiomeSourceType.THE_END.getConfig(world.getSeed()))), config);
    }
    
    private static void deleteBlocks(ProtoChunk chunk, IWorld world)
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
        if (world.getDimension().getType() == DimensionType.THE_END)
            StructureHelper.generatePillars(chunk, world, ((TheEndDimension) world.getDimension()).getEnderDragonFight());
        Heightmap.populateHeightmaps(chunk, EnumSet.allOf(Heightmap.Type.class));
    }

    private static void clearChunk(ProtoChunk chunk, IWorld world)
    {
        deleteBlocks(chunk, world);
        // erase entities
        if (world.getDimension().getType() != DimensionType.THE_END)
        {
            chunk.getEntities().clear();
        }
        else
        {
            chunk.getEntities().removeIf(tag -> {
                String id = tag.getString("id");
               if (id.equals("minecraft:end_crystal") || id.equals("minecraft:shulker"))
               {
                   return false;
               }
               else if (id.equals("minecraft:item_frame"))
               {
                   tag.putBoolean("Fixed", true);
                   return false;
               }
               else
               {
                   return true;
               }
            });
        }

        try
        {
            ((ServerLightingProvider) chunk.getLightingProvider()).light(chunk, true).get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }
    
    public static class SkyBlockOverworldGenerator extends OverworldChunkGenerator
    {
        public SkyBlockOverworldGenerator(IWorld world, BiomeSource biomeSource, OverworldChunkGeneratorConfig config)
        {
            super(world, biomeSource, config);
        }
        
        @Override
        public void populateEntities(ChunkRegion region)
        {
            ProtoChunk chunk = (ProtoChunk) region.getChunk(region.getCenterChunkX(), region.getCenterChunkZ());
            clearChunk(chunk, world);
        }
    }
    
    public static class SkyBlockCavesGenerator extends CavesChunkGenerator
    {
        public SkyBlockCavesGenerator(World world, BiomeSource biomeSource, CavesChunkGeneratorConfig config)
        {
            super(world, biomeSource, config);
        }
        
        @Override
        public void populateEntities(ChunkRegion region)
        {
            ProtoChunk chunk = (ProtoChunk) region.getChunk(region.getCenterChunkX(), region.getCenterChunkZ());
            clearChunk(chunk, world);
        }
    }
    
    public static class SkyBlockFloatingIslandsGenerator extends FloatingIslandsChunkGenerator
    {
        public SkyBlockFloatingIslandsGenerator(World world, BiomeSource biomeSource, FloatingIslandsChunkGeneratorConfig config)
        {
            super(world, biomeSource, config);
        }
    
        @Override
        public void populateEntities(ChunkRegion region)
        {
            ProtoChunk chunk = (ProtoChunk) region.getChunk(region.getCenterChunkX(), region.getCenterChunkZ());
            clearChunk(chunk, world);
        }
    }
}
