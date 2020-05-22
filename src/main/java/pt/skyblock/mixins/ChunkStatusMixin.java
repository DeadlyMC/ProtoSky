package pt.skyblock.mixins;

import net.minecraft.server.world.ServerLightingProvider;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureManager;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.skyblock.gen.WorldGenUtils;

import java.util.List;
import java.util.function.Function;

@Mixin(ChunkStatus.class)
public abstract class ChunkStatusMixin
{
    // LIGHT
    @Inject(method = "method_20613", at = @At("HEAD"))
    private static void onLighting(ChunkStatus chunkStatus, ServerWorld world, ChunkGenerator generator, StructureManager manager, ServerLightingProvider lightingProvider, Function function, List list, Chunk chunk, CallbackInfoReturnable info)
    {
        if(!chunk.getStatus().isAtLeast(chunkStatus))
            WorldGenUtils.deleteBlocks((ProtoChunk) chunk, world);
    }

    @Inject(method = "method_16566", at = @At("RETURN"))
    private static void afterPopulation(ServerWorld world, ChunkGenerator generator, List list, Chunk chunk, CallbackInfo info) {
        WorldGenUtils.clearEntities((ProtoChunk)chunk, world);
    }
}
