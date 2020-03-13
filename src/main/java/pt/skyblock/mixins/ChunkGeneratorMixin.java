package pt.skyblock.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.level.LevelGeneratorType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pt.skyblock.gen.WorldGenUtils;

@Mixin(ChunkGenerator.class)
public abstract class ChunkGeneratorMixin
{
    @Shadow @Final protected IWorld world;
    
    // CREDITS : @modmuss50
    @Inject(method = "populateEntities", at = @At("RETURN"))
    public void onPopulateEntities(ChunkRegion region, CallbackInfo ci)
    {
        LevelGeneratorType type = this.world.getLevelProperties().getGeneratorType();
        if (type != WorldGenUtils.LEVEL_GENERATOR_TYPE || region.getWorld().getDimension().getType() != DimensionType.THE_END)
        {
            return;
        }
        
        int chunkX = region.getCenterChunkX() * 16;
        int chunkZ = region.getCenterChunkZ() * 16;
        
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        
        //Remove all blocks we dont want
        for (int x = 0; x < 16; x++)
        {
            for (int z = 0; z < 16; z++)
            {
                for (int y = 0; y < 256; y++)
                {
                    mutable.set(chunkX + x, y, chunkZ + z);
                    Block block = region.getBlockState(mutable).getBlock();
                    if (block == Blocks.END_STONE || block == Blocks.CHORUS_FLOWER || block == Blocks.CHORUS_PLANT)
                    {
                        region.setBlockState(mutable, Blocks.AIR.getDefaultState(), 0);
                    }
                }
            }
        }
    }
}
