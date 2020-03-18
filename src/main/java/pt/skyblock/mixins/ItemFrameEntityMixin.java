package pt.skyblock.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameEntityMixin extends AbstractDecorationEntity
{
    @Unique
    private boolean doNotPop = false;
    
    protected ItemFrameEntityMixin(EntityType<? extends AbstractDecorationEntity> entityType, World world)
    {
        super(entityType, world);
    }
    
    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    private void onReadCustomDataFromTag(CompoundTag tag, CallbackInfo ci)
    {
        if (tag.contains("NoPop"))
            this.doNotPop = tag.getBoolean("NoPop");
    }
    
    @Inject(method = "canStayAttached", at = @At("HEAD"), cancellable = true)
    private void onCanStayAttached(CallbackInfoReturnable<Boolean> cir)
    {
        if (this.doNotPop)
            cir.setReturnValue(true);
    }
}
