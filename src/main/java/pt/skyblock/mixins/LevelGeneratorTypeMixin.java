package pt.skyblock.mixins;

import net.minecraft.world.level.LevelGeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import pt.skyblock.gen.WorldGenUtils;

@Mixin(LevelGeneratorType.class)
public class LevelGeneratorTypeMixin
{
    private LevelGeneratorTypeMixin(int id, String name)
    {
    }
    
    static
    {
        WorldGenUtils.LEVEL_GENERATOR_TYPE = (LevelGeneratorType) (Object) new LevelGeneratorTypeMixin(15, "skyblock");
    }
}
