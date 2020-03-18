package pt.skyblock.mixins;

import com.mojang.datafixers.Dynamic;
import net.minecraft.world.level.LevelGeneratorOptions;
import net.minecraft.world.level.LevelGeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.skyblock.gen.WorldGenUtils;

@Mixin(LevelGeneratorOptions.class)
public abstract class LevelGeneratorOptionsMixin
{
    @Inject(method = "createDefault", at = @At("HEAD"), cancellable = true)
    private static void onCreateDefault(LevelGeneratorType generatorType, Dynamic<?> dynamic, CallbackInfoReturnable<LevelGeneratorOptions> cir)
    {
        cir.setReturnValue(WorldGenUtils.createOverworldChunkGenerator(generatorType, dynamic));
        cir.cancel();
    }
}
