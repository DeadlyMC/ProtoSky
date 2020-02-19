package pt.skyblock.mixins;

import net.minecraft.structure.NetherFortressGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NetherFortressGenerator.BridgeEnd.class)
public interface BridgeEndAccessor
{
    @Accessor("seed")
    int getSeed();
}
