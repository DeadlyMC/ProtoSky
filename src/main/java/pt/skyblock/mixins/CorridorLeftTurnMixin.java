package pt.skyblock.mixins;

import net.minecraft.structure.NetherFortressGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import pt.skyblock.util.ICorridorLeftTurn;

@Mixin(NetherFortressGenerator.CorridorLeftTurn.class)
public abstract class CorridorLeftTurnMixin implements ICorridorLeftTurn
{
    @Shadow private boolean containsChest;
    
    @Override
    public boolean getContainsChest()
    {
        return this.containsChest;
    }
    
    @Override
    public void setContainsChest(boolean containsChest)
    {
        this.containsChest = containsChest;
    }
}
