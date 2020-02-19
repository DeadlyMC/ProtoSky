package pt.skyblock.mixins;

import net.minecraft.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pt.skyblock.PTSkyblockRegistry;

@Mixin(targets = "net/minecraft/structure/StrongholdGenerator$StoneBrickRandomizer")
public abstract class StoneBrickRandomizerMixin
{
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onCtor(CallbackInfo ci)
    {
        PTSkyblockRegistry.stoneBrickRandomizer = (StructurePiece.BlockRandomizer) (Object) this;
    }
}