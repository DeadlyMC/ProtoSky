package pt.skyblock.gen;

import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerLightingProvider;
import net.minecraft.util.PackedIntegerArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSourceConfig;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.dimension.TheEndDimension;
import net.minecraft.world.gen.chunk.*;
import net.minecraft.world.level.LevelGeneratorType;
import pt.skyblock.mixins.ProtoChunkAccessor;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class WorldGenUtils
{
    public static LevelGeneratorType LEVEL_GENERATOR_TYPE;
    
    public static ChunkGenerator<? extends ChunkGeneratorConfig> createOverworldChunkGenerator(World world)
    {
        ChunkGeneratorType<OverworldChunkGeneratorConfig, OverworldChunkGenerator> chunkGeneratorType = ChunkGeneratorType.SURFACE;
        BiomeSourceType<VanillaLayeredBiomeSourceConfig, VanillaLayeredBiomeSource> biomeSourceType = BiomeSourceType.VANILLA_LAYERED;
        OverworldChunkGeneratorConfig chunkGeneratorConfig = chunkGeneratorType.createSettings();
        VanillaLayeredBiomeSourceConfig biomeSourceConfig = biomeSourceType.getConfig(world.getLevelProperties()).setGeneratorSettings(chunkGeneratorConfig);
        return new SkyBlockOverworldGenerator(world, biomeSourceType.applyConfig(biomeSourceConfig), chunkGeneratorConfig);
    }
    
    public static ChunkGenerator<? extends ChunkGeneratorConfig> createNetherChunkGenerator(World world)
    {
        CavesChunkGeneratorConfig config = ChunkGeneratorType.CAVES.createSettings();
        config.setDefaultBlock(Blocks.NETHERRACK.getDefaultState());
        config.setDefaultFluid(Blocks.LAVA.getDefaultState());
        return new SkyBlockCavesGenerator(world, BiomeSourceType.FIXED.applyConfig((BiomeSourceType.FIXED.getConfig(world.getLevelProperties())).setBiome(Biomes.NETHER)), config);
    }
    
    public static ChunkGenerator<? extends ChunkGeneratorConfig> createEndChunkGenerator(World world)
    {
        FloatingIslandsChunkGeneratorConfig config = ChunkGeneratorType.FLOATING_ISLANDS.createSettings();
        config.setDefaultBlock(Blocks.END_STONE.getDefaultState());
        config.setDefaultFluid(Blocks.AIR.getDefaultState());
        config.withCenter(TheEndDimension.SPAWN_POINT);
        return new SkyBlockFloatingIslandsGenerator(world, BiomeSourceType.THE_END.applyConfig((BiomeSourceType.THE_END.getConfig(world.getLevelProperties()))), config);
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
        StructureHelper.processNetherFortress(chunk, world);
        Heightmap.populateHeightmaps(chunk, EnumSet.allOf(Heightmap.Type.class));
    }

    private static void clearChunk(ProtoChunk chunk, IWorld world)
    {
        deleteBlocks(chunk, world);
        // erase entities
        chunk.getEntities().clear();

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
    }
}
