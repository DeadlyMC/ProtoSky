package pt.skyblock.mixins;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StrongholdGenerator.SmallCorridor.class)
public interface SmallCorridorAccessor
{
    @Accessor("length")
    int getLength();
}
