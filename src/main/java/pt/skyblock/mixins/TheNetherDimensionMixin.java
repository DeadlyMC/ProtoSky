package pt.skyblock.mixins;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.TheNetherDimension;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.skyblock.gen.WorldGenUtils;

@Mixin(TheNetherDimension.class)
public abstract class TheNetherDimensionMixin extends Dimension
{
    public TheNetherDimensionMixin(World world, DimensionType type)
    {
        super(world, type, 0.1f);
        throw new AbstractMethodError();
    }
    
    @Inject(method = "createChunkGenerator", at = @At("HEAD"), cancellable = true)
    private void createSkyBlockGenerator(CallbackInfoReturnable<ChunkGenerator<? extends ChunkGeneratorConfig>> cir)
    {
        cir.setReturnValue(WorldGenUtils.createNetherChunkGenerator(this.world));
        cir.cancel();
    }
}
