package pt.skyblock.mixins;

import com.mojang.datafixers.Dynamic;
import net.minecraft.world.level.LevelGeneratorOptions;
import net.minecraft.world.level.LevelGeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import pt.skyblock.gen.WorldGenUtils;

import java.util.function.BiFunction;

@Mixin(LevelGeneratorType.class)
public class LevelGeneratorTypeMixin
{
    private LevelGeneratorTypeMixin(int index, String name, BiFunction<LevelGeneratorType, Dynamic<?>, LevelGeneratorOptions> optionsFactory)
    {
    
    }
    
    static
    {
        WorldGenUtils.LEVEL_GENERATOR_TYPE = (LevelGeneratorType) (Object) new LevelGeneratorTypeMixin(15, "skyblock", WorldGenUtils::createOverworldChunkGenerator);
    }
}
