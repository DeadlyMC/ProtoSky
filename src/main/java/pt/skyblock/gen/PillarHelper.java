package pt.skyblock.gen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.feature.EndSpikeFeature;

import java.util.Random;

public class PillarHelper
{
    public static void generateSpike(ProtoChunk chunk, IWorld world, Random random, EndSpikeFeature.Spike spike, EnderDragonFight fight)
    {
        int i = spike.getRadius();
    
        for (BlockPos blockPos : BlockPos.iterate(new BlockPos(spike.getCenterX() - i, 0, spike.getCenterZ() - i), new BlockPos(spike.getCenterX() + i, spike.getHeight() + 10, spike.getCenterZ() + i)))
        {
            if (blockPos.getSquaredDistance((double) spike.getCenterX(), (double) blockPos.getY(), (double) spike.getCenterZ(), false) <= (double) (i * i + 1) && blockPos.getY() < spike.getHeight())
            {
                StructureHelper.setBlockInChunk(chunk, blockPos, Blocks.OBSIDIAN.getDefaultState());
            }
            else if (blockPos.getY() > 65)
            {
                StructureHelper.setBlockInChunk(chunk, blockPos, Blocks.AIR.getDefaultState());
            }
        }
        
        if (spike.isGuarded())
        {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
    
            for (int m = -2; m <= 2; ++m)
            {
                for (int n = -2; n <= 2; ++n)
                {
                    for (int o = 0; o <= 3; ++o)
                    {
                        boolean bl = MathHelper.abs(m) == 2;
                        boolean bl2 = MathHelper.abs(n) == 2;
                        boolean bl3 = o == 3;
                        if (bl || bl2 || bl3)
                        {
                            boolean bl4 = m == -2 || m == 2 || bl3;
                            boolean bl5 = n == -2 || n == 2 || bl3;
                            BlockState blockState = (BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, bl4 && n != -2).with(PaneBlock.SOUTH, bl4 && n != 2).with(PaneBlock.WEST, bl5 && m != -2).with(PaneBlock.EAST, bl5 && m != 2);
                            StructureHelper.setBlockInChunk(chunk, mutable.set(spike.getCenterX() + m, spike.getHeight() + o, spike.getCenterZ() + n), blockState);
                        }
                    }
                }
            }
        }
        
        BlockPos crystalPos = new BlockPos(spike.getCenterX(), spike.getHeight(), spike.getCenterZ());
        StructureHelper.setBlockInChunk(chunk, crystalPos, Blocks.BEDROCK.getDefaultState());
    }
}