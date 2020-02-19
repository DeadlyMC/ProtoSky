package pt.skyblock.mixins;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StrongholdGenerator.SquareRoom.class)
public interface SquareRoomAccessor
{
    @Accessor("roomType")
    int getRoomType();
}
