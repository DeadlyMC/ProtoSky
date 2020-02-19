package pt.skyblock.mixins;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StrongholdGenerator.Corridor.class)
public interface CorridorAccessor
{
    @Accessor("leftExitExists")
    boolean getLeftExitExists();
    
    @Accessor("rightExitExixts")
    boolean getRightExitExixts();
}
