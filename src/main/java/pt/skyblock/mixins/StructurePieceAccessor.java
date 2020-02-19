package pt.skyblock.mixins;

import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Random;

@Mixin(StructurePiece.class)
public interface StructurePieceAccessor
{
    @Accessor("mirror")
    BlockMirror getMirror();
    
    @Invoker
    int invokeApplyXTransform(int x, int z);
    
    @Invoker
    int invokeApplyYTransform(int y);
    
    @Invoker
    int invokeApplyZTransform(int x, int z);
}
