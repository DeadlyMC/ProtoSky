package pt.skyblock.mixins;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StrongholdGenerator.FiveWayCrossing.class)
public interface FiveWayCrossingAccessor
{
    @Accessor("lowerLeftExists")
    boolean getLowerLeftExists();
    
    @Accessor("upperLeftExists")
    boolean getUpperLeftExists();
    
    @Accessor("lowerRightExists")
    boolean getLowerRightExists();
    
    @Accessor("upperRightExists")
    boolean getUpperRightExists();
}
