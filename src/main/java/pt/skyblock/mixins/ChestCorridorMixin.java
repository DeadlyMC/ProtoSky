package pt.skyblock.mixins;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import pt.skyblock.util.IChestCorridor;

@Mixin(StrongholdGenerator.ChestCorridor.class)
public abstract class ChestCorridorMixin implements IChestCorridor
{
    @Shadow private boolean chestGenerated;
    
    @Override
    public boolean getChestGenerated()
    {
        return this.chestGenerated;
    }
    
    @Override
    public void setChestGenerated(boolean chestGenerated)
    {
        this.chestGenerated = chestGenerated;
    }
}
