package pt.skyblock.gen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.structure.NetherFortressGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.ProtoChunk;
import pt.skyblock.mixins.BridgeEndAccessor;
import pt.skyblock.mixins.StructurePieceAccessor;
import pt.skyblock.util.ICorridorLeftTurn;
import pt.skyblock.util.ICorridorRightTurn;

import java.util.Random;

import static pt.skyblock.gen.StructureHelper.*;

// Redundant class - For future use
public class FortressHelper
{
    public static void generateBridge(ProtoChunk chunk, NetherFortressGenerator.Bridge bridge, Random random)
    {
        fillWithOutline(chunk, 0, 3, 0, 4, 4, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridge);
        fillWithOutline(chunk, 1, 5, 0, 3, 7, 18, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, bridge);
        fillWithOutline(chunk, 0, 5, 0, 0, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridge);
        fillWithOutline(chunk, 4, 5, 0, 4, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridge);
        fillWithOutline(chunk, 0, 2, 0, 4, 2, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridge);
        fillWithOutline(chunk, 0, 2, 13, 4, 2, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridge);
        fillWithOutline(chunk, 0, 0, 0, 4, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridge);
        fillWithOutline(chunk, 0, 0, 15, 4, 1, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridge);
    
        for (int i = 0; i <= 4; ++i)
        {
            for (int j = 0; j <= 2; ++j)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, bridge);
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, 18 - j, bridge);
            }
        }
    
        BlockState blockState = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
        BlockState blockState2 = (BlockState) blockState.with(FenceBlock.EAST, true);
        BlockState blockState3 = (BlockState) blockState.with(FenceBlock.WEST, true);
        fillWithOutline(chunk, 0, 1, 1, 0, 4, 1, blockState2, blockState2, false, bridge);
        fillWithOutline(chunk, 0, 3, 4, 0, 4, 4, blockState2, blockState2, false, bridge);
        fillWithOutline(chunk, 0, 3, 14, 0, 4, 14, blockState2, blockState2, false, bridge);
        fillWithOutline(chunk, 0, 1, 17, 0, 4, 17, blockState2, blockState2, false, bridge);
        fillWithOutline(chunk, 4, 1, 1, 4, 4, 1, blockState3, blockState3, false, bridge);
        fillWithOutline(chunk, 4, 3, 4, 4, 4, 4, blockState3, blockState3, false, bridge);
        fillWithOutline(chunk, 4, 3, 14, 4, 4, 14, blockState3, blockState3, false, bridge);
        fillWithOutline(chunk, 4, 1, 17, 4, 4, 17, blockState3, blockState3, false, bridge);
    }
    
    public static void generateBridgeCrossing(ProtoChunk chunk, NetherFortressGenerator.BridgeCrossing crossing, Random random)
    {
        fillWithOutline(chunk, 7, 3, 0, 11, 4, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 0, 3, 7, 18, 4, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 8, 5, 0, 10, 7, 18, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 0, 5, 8, 18, 7, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 7, 5, 0, 7, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 7, 5, 11, 7, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 11, 5, 0, 11, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 11, 5, 11, 11, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 0, 5, 7, 7, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 11, 5, 7, 18, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 0, 5, 11, 7, 5, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 11, 5, 11, 18, 5, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 7, 2, 0, 11, 2, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 7, 2, 13, 11, 2, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 7, 0, 0, 11, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 7, 0, 15, 11, 1, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
    
        int k;
        int l;
        for (k = 7; k <= 11; ++k)
        {
            for (l = 0; l <= 2; ++l)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), k, -1, l, crossing);
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), k, -1, 18 - l, crossing);
            }
        }
    
        fillWithOutline(chunk, 0, 2, 7, 5, 2, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 13, 2, 7, 18, 2, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 0, 0, 7, 3, 1, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 15, 0, 7, 18, 1, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
    
        for (k = 0; k <= 2; ++k)
        {
            for (l = 7; l <= 11; ++l)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), k, -1, l, crossing);
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), 18 - k, -1, l, crossing);
            }
        }
    }
    
    public static void generateBridgeSmallCrossing(ProtoChunk chunk, NetherFortressGenerator.BridgeSmallCrossing smallCrossing, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 0, 2, 0, 6, 7, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 0, 2, 0, 1, 6, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 0, 2, 6, 1, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 5, 2, 0, 6, 6, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 5, 2, 6, 6, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 0, 2, 0, 0, 6, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 0, 2, 5, 0, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 6, 2, 0, 6, 6, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 6, 2, 5, 6, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        BlockState blockState = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.EAST, true);
        BlockState blockState2 = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
        fillWithOutline(chunk, 2, 6, 0, 4, 6, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 2, 5, 0, 4, 5, 0, blockState, blockState, false, smallCrossing);
        fillWithOutline(chunk, 2, 6, 6, 4, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 2, 5, 6, 4, 5, 6, blockState, blockState, false, smallCrossing);
        fillWithOutline(chunk, 0, 6, 2, 0, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 0, 5, 2, 0, 5, 4, blockState2, blockState2, false, smallCrossing);
        fillWithOutline(chunk, 6, 6, 2, 6, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCrossing);
        fillWithOutline(chunk, 6, 5, 2, 6, 5, 4, blockState2, blockState2, false, smallCrossing);
    
        for (int i = 0; i <= 6; ++i)
        {
            for (int j = 0; j <= 6; ++j)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, smallCrossing);
            }
        }
    }
    
    public static void generateBridgeStairs(ProtoChunk chunk, NetherFortressGenerator.BridgeStairs bridgeStairs, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 0, 2, 0, 6, 10, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 0, 2, 0, 1, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 5, 2, 0, 6, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 0, 2, 1, 0, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 6, 2, 1, 6, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 1, 2, 6, 5, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        BlockState blockState = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.EAST, true);
        BlockState blockState2 = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
        fillWithOutline(chunk, 0, 3, 2, 0, 5, 4, blockState2, blockState2, false, bridgeStairs);
        fillWithOutline(chunk, 6, 3, 2, 6, 5, 2, blockState2, blockState2, false, bridgeStairs);
        fillWithOutline(chunk, 6, 3, 4, 6, 5, 4, blockState2, blockState2, false, bridgeStairs);
        setBlockInStructure(bridgeStairs, chunk, Blocks.NETHER_BRICKS.getDefaultState(), 5, 2, 5);
        fillWithOutline(chunk, 4, 2, 5, 4, 3, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 3, 2, 5, 3, 4, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 2, 2, 5, 2, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 1, 2, 5, 1, 6, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 1, 7, 1, 5, 7, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 6, 8, 2, 6, 8, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 2, 6, 0, 4, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeStairs);
        fillWithOutline(chunk, 2, 5, 0, 4, 5, 0, blockState, blockState, false, bridgeStairs);
    
        for (int i = 0; i <= 6; ++i)
        {
            for (int j = 0; j <= 6; ++j)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, bridgeStairs);
            }
        }
    }
    
    public static void generateBridgePlatform(ProtoChunk chunk, NetherFortressGenerator.BridgePlatform bridgePlatform, Random random)
    {
        fillWithOutline(chunk, 0, 2, 0, 6, 7, 7, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 1, 0, 0, 5, 1, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 1, 2, 1, 5, 2, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 1, 3, 2, 5, 3, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 1, 4, 3, 5, 4, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 1, 2, 0, 1, 4, 2, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 5, 2, 0, 5, 4, 2, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 1, 5, 2, 1, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 5, 5, 2, 5, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 0, 5, 3, 0, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 6, 5, 3, 6, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        fillWithOutline(chunk, 1, 5, 8, 5, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgePlatform);
        BlockState blockState = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.EAST, true);
        BlockState blockState2 = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
        setBlockInStructure(bridgePlatform, chunk, (BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true), 1, 6, 3);
        setBlockInStructure(bridgePlatform, chunk, (BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.EAST, true), 5, 6, 3);
        setBlockInStructure(bridgePlatform, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.EAST, true)).with(FenceBlock.NORTH, true), 0, 6, 3);
        setBlockInStructure(bridgePlatform, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.NORTH, true), 6, 6, 3);
        fillWithOutline(chunk, 0, 6, 4, 0, 6, 7, blockState2, blockState2, false, bridgePlatform);
        fillWithOutline(chunk, 6, 6, 4, 6, 6, 7, blockState2, blockState2, false, bridgePlatform);
        setBlockInStructure(bridgePlatform, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.EAST, true)).with(FenceBlock.SOUTH, true), 0, 6, 8);
        setBlockInStructure(bridgePlatform, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.SOUTH, true), 6, 6, 8);
        fillWithOutline(chunk, 1, 6, 8, 5, 6, 8, blockState, blockState, false, bridgePlatform);
        setBlockInStructure(bridgePlatform, chunk, (BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.EAST, true), 1, 7, 8);
        fillWithOutline(chunk, 2, 7, 8, 4, 7, 8, blockState, blockState, false, bridgePlatform);
        setBlockInStructure(bridgePlatform, chunk, (BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true), 5, 7, 8);
        setBlockInStructure(bridgePlatform, chunk, (BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.EAST, true), 2, 8, 8);
        setBlockInStructure(bridgePlatform, chunk, blockState, 3, 8, 8);
        setBlockInStructure(bridgePlatform, chunk, (BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true), 4, 8, 8);
        
        BlockPos spawnerPos = getBlockInStructurePiece(bridgePlatform, 3, 5, 5);
        setBlockInChunk(chunk, spawnerPos, Blocks.SPAWNER.getDefaultState());
        CompoundTag spawnerTag = new CompoundTag();
        spawnerTag.putString("id", "minecraft:mob_spawner");
        ListTag spawnPotentials = new ListTag();
        spawnerTag.put("SpawnPotentials", spawnPotentials);
        CompoundTag spawnEntry = new CompoundTag();
        spawnPotentials.addTag(0, spawnEntry);
        CompoundTag entity = new CompoundTag();
        spawnEntry.put("Entity", entity);
        entity.putString("id", "minecraft:blaze");
        spawnEntry.putInt("Weight", 1);
        spawnerTag.put("SpawnData", entity.copy());
        setBlockEntityInChunk(chunk, spawnerPos, spawnerTag);
    
        for (int i = 0; i <= 6; ++i)
        {
            for (int j = 0; j <= 6; ++j)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, bridgePlatform);
            }
        }
    }
    
    public static void generateCorridorExit(ProtoChunk chunk, NetherFortressGenerator.CorridorExit corridorExit, Random random)
    {
        fillWithOutline(chunk, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 0, 5, 0, 12, 13, 12, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 5, 8, 0, 7, 8, 0, Blocks.NETHER_BRICK_FENCE.getDefaultState(), Blocks.NETHER_BRICK_FENCE.getDefaultState(), false, corridorExit);
        BlockState blockState = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.EAST, true);
        BlockState blockState2 = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
    
        int m;
        for (m = 1; m <= 11; m += 2)
        {
            fillWithOutline(chunk, m, 10, 0, m, 11, 0, blockState, blockState, false, corridorExit);
            fillWithOutline(chunk, m, 10, 12, m, 11, 12, blockState, blockState, false, corridorExit);
            fillWithOutline(chunk, 0, 10, m, 0, 11, m, blockState2, blockState2, false, corridorExit);
            fillWithOutline(chunk, 12, 10, m, 12, 11, m, blockState2, blockState2, false, corridorExit);
            setBlockInStructure(corridorExit, chunk, Blocks.NETHER_BRICKS.getDefaultState(), m, 13, 0);
            setBlockInStructure(corridorExit, chunk, Blocks.NETHER_BRICKS.getDefaultState(), m, 13, 12);
            setBlockInStructure(corridorExit, chunk, Blocks.NETHER_BRICKS.getDefaultState(), 0, 13, m);
            setBlockInStructure(corridorExit, chunk, Blocks.NETHER_BRICKS.getDefaultState(), 12, 13, m);
            if (m != 11)
            {
                setBlockInStructure(corridorExit, chunk, blockState, m + 1, 13, 0);
                setBlockInStructure(corridorExit, chunk, blockState, m + 1, 13, 12);
                setBlockInStructure(corridorExit, chunk, blockState2, 0, 13, m + 1);
                setBlockInStructure(corridorExit, chunk, blockState2, 12, 13, m + 1);
            }
        }
    
        setBlockInStructure(corridorExit, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.EAST, true), 0, 13, 0);
        setBlockInStructure(corridorExit, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.SOUTH, true)).with(FenceBlock.EAST, true), 0, 13, 12);
        setBlockInStructure(corridorExit, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.SOUTH, true)).with(FenceBlock.WEST, true), 12, 13, 12);
        setBlockInStructure(corridorExit, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.WEST, true), 12, 13, 0);
    
        for (m = 3; m <= 9; m += 2)
        {
            fillWithOutline(chunk, 1, 7, m, 1, 8, m, (BlockState) blockState2.with(FenceBlock.WEST, true), (BlockState) blockState2.with(FenceBlock.WEST, true), false, corridorExit);
            fillWithOutline(chunk, 11, 7, m, 11, 8, m, (BlockState) blockState2.with(FenceBlock.EAST, true), (BlockState) blockState2.with(FenceBlock.EAST, true), false, corridorExit);
        }
    
        fillWithOutline(chunk, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
    
        int n;
        for (m = 4; m <= 8; ++m)
        {
            for (n = 0; n <= 2; ++n)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), m, -1, n, corridorExit);
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), m, -1, 12 - n, corridorExit);
            }
        }
    
        for (m = 0; m <= 2; ++m)
        {
            for (n = 4; n <= 8; ++n)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), m, -1, n, corridorExit);
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), 12 - m, -1, n, corridorExit);
            }
        }
    
        fillWithOutline(chunk, 5, 5, 5, 7, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorExit);
        fillWithOutline(chunk, 6, 1, 6, 6, 4, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, corridorExit);
        setBlockInStructure(corridorExit, chunk, Blocks.NETHER_BRICKS.getDefaultState(), 6, 0, 6);
        setBlockInStructure(corridorExit, chunk, Blocks.LAVA.getDefaultState(), 6, 5, 6);
        StructurePieceAccessor access = (StructurePieceAccessor) corridorExit;
        BlockPos blockPos = new BlockPos(access.invokeApplyXTransform(6, 6), access.invokeApplyYTransform(5), access.invokeApplyZTransform(6, 6));
        if (corridorExit.getBoundingBox().contains(blockPos))
        {
            chunk.getFluidTickScheduler().schedule(blockPos, Fluids.LAVA, 0);
        }
    }
    
    public static void generateSmallCorridor(ProtoChunk chunk, NetherFortressGenerator.SmallCorridor smallCorridor, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCorridor);
        fillWithOutline(chunk, 0, 2, 0, 4, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, smallCorridor);
        BlockState blockState = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
        fillWithOutline(chunk, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCorridor);
        fillWithOutline(chunk, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCorridor);
        fillWithOutline(chunk, 0, 3, 1, 0, 4, 1, blockState, blockState, false, smallCorridor);
        fillWithOutline(chunk, 0, 3, 3, 0, 4, 3, blockState, blockState, false, smallCorridor);
        fillWithOutline(chunk, 4, 3, 1, 4, 4, 1, blockState, blockState, false, smallCorridor);
        fillWithOutline(chunk, 4, 3, 3, 4, 4, 3, blockState, blockState, false, smallCorridor);
        fillWithOutline(chunk, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, smallCorridor);
    
        for (int i = 0; i <= 4; ++i)
        {
            for (int j = 0; j <= 4; ++j)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, smallCorridor);
            }
        }
    }
    
    public static void generateCorridorRightTurn(ProtoChunk chunk, NetherFortressGenerator.CorridorRightTurn rightTurn, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, rightTurn);
        fillWithOutline(chunk, 0, 2, 0, 4, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, rightTurn);
        BlockState blockState = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.EAST, true);
        BlockState blockState2 = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
        fillWithOutline(chunk, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, rightTurn);
        fillWithOutline(chunk, 0, 3, 1, 0, 4, 1, blockState2, blockState2, false, rightTurn);
        fillWithOutline(chunk, 0, 3, 3, 0, 4, 3, blockState2, blockState2, false, rightTurn);
        fillWithOutline(chunk, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, rightTurn);
        fillWithOutline(chunk, 1, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, rightTurn);
        fillWithOutline(chunk, 1, 3, 4, 1, 4, 4, blockState, blockState, false, rightTurn);
        fillWithOutline(chunk, 3, 3, 4, 3, 4, 4, blockState, blockState, false, rightTurn);
        StructurePieceAccessor access = (StructurePieceAccessor) rightTurn;
        if (((ICorridorRightTurn) rightTurn).getContainsChest() && rightTurn.getBoundingBox().contains(new BlockPos(access.invokeApplyXTransform(1, 3), access.invokeApplyYTransform(2), access.invokeApplyZTransform(1, 3))))
        {
            ((ICorridorRightTurn) rightTurn).setContainsChest(false);
            addChest(chunk, random, 1, 2, 3, LootTables.NETHER_BRIDGE_CHEST, (BlockState)null, rightTurn);
        }
    
        fillWithOutline(chunk, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, rightTurn);
    
        for (int i = 0; i <= 4; ++i)
        {
            for (int j = 0; j <= 4; ++j)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, rightTurn);
            }
        }
    }
    
    public static void generateCorridorLeftTurn(ProtoChunk chunk, NetherFortressGenerator.CorridorLeftTurn leftTurn, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, leftTurn);
        fillWithOutline(chunk, 0, 2, 0, 4, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, leftTurn);
        BlockState blockState = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.EAST, true);
        BlockState blockState2 = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
        fillWithOutline(chunk, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, leftTurn);
        fillWithOutline(chunk, 4, 3, 1, 4, 4, 1, blockState2, blockState2, false, leftTurn);
        fillWithOutline(chunk, 4, 3, 3, 4, 4, 3, blockState2, blockState2, false, leftTurn);
        fillWithOutline(chunk, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, leftTurn);
        fillWithOutline(chunk, 0, 2, 4, 3, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, leftTurn);
        fillWithOutline(chunk, 1, 3, 4, 1, 4, 4, blockState, blockState, false, leftTurn);
        fillWithOutline(chunk, 3, 3, 4, 3, 4, 4, blockState, blockState, false, leftTurn);
    
        StructurePieceAccessor access = (StructurePieceAccessor) leftTurn;
        if (((ICorridorLeftTurn) leftTurn).getContainsChest() && leftTurn.getBoundingBox().contains(new BlockPos(access.invokeApplyXTransform(3, 3), access.invokeApplyYTransform(2), access.invokeApplyZTransform(3, 3))))
        {
            ((ICorridorLeftTurn) leftTurn).setContainsChest(false);
            addChest(chunk, random, 3, 2, 3, LootTables.NETHER_BRIDGE_CHEST, (BlockState)null, leftTurn);
        }
    
        fillWithOutline(chunk, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, leftTurn);
    
        for (int i = 0; i <= 4; ++i)
        {
            for (int j = 0; j <= 4; ++j)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, leftTurn);
            }
        }
    }
    
    public static void generateCorridorStairs(ProtoChunk chunk, NetherFortressGenerator.CorridorStairs corridorStairs, Random random)
    {
        BlockState blockState = (BlockState) Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);
        BlockState blockState2 = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
    
        for (int i = 0; i <= 9; ++i)
        {
            int j = Math.max(1, 7 - i);
            int k = Math.min(Math.max(j + 5, 14 - i), 13);
            int l = i;
            fillWithOutline(chunk, 0, 0, i, 4, j, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorStairs);
            fillWithOutline(chunk, 1, j + 1, i, 3, k - 1, i, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, corridorStairs);
            if (i <= 6)
            {
                setBlockInStructure(corridorStairs, chunk, blockState, 1, j + 1, i);
                setBlockInStructure(corridorStairs, chunk, blockState, 2, j + 1, i);
                setBlockInStructure(corridorStairs, chunk, blockState, 3, j + 1, i);
            }
        
            fillWithOutline(chunk, 0, k, i, 4, k, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorStairs);
            fillWithOutline(chunk, 0, j + 1, i, 0, k - 1, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorStairs);
            fillWithOutline(chunk, 4, j + 1, i, 4, k - 1, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, corridorStairs);
            if ((i & 1) == 0)
            {
                fillWithOutline(chunk, 0, j + 2, i, 0, j + 3, i, blockState2, blockState2, false, corridorStairs);
                fillWithOutline(chunk, 4, j + 2, i, 4, j + 3, i, blockState2, blockState2, false, corridorStairs);
            }
        
            for (int m = 0; m <= 4; ++m)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), m, -1, l, corridorStairs);
            }
        }
    }
    
    public static void generateCorridorBalcony(ProtoChunk chunk, NetherFortressGenerator.CorridorBalcony balcony, Random random)
    {
        BlockState blockState = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
        BlockState blockState2 = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.EAST, true);
        fillWithOutline(chunk, 0, 0, 0, 8, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 0, 2, 0, 8, 5, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 0, 6, 0, 8, 6, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 0, 2, 0, 2, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 6, 2, 0, 8, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 1, 3, 0, 1, 4, 0, blockState2, blockState2, false, balcony);
        fillWithOutline(chunk, 7, 3, 0, 7, 4, 0, blockState2, blockState2, false, balcony);
        fillWithOutline(chunk, 0, 2, 4, 8, 2, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 1, 1, 4, 2, 2, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 6, 1, 4, 7, 2, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 1, 3, 8, 7, 3, 8, blockState2, blockState2, false, balcony);
        setBlockInStructure(balcony, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.EAST, true)).with(FenceBlock.SOUTH, true), 0, 3, 8);
        setBlockInStructure(balcony, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.SOUTH, true), 8, 3, 8);
        fillWithOutline(chunk, 0, 3, 6, 0, 3, 7, blockState, blockState, false, balcony);
        fillWithOutline(chunk, 8, 3, 6, 8, 3, 7, blockState, blockState, false, balcony);
        fillWithOutline(chunk, 0, 3, 4, 0, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 8, 3, 4, 8, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 1, 3, 5, 2, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 6, 3, 5, 7, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, balcony);
        fillWithOutline(chunk, 1, 4, 5, 1, 5, 5, blockState2, blockState2, false, balcony);
        fillWithOutline(chunk, 7, 4, 5, 7, 5, 5, blockState2, blockState2, false, balcony);
    
        for (int i = 0; i <= 5; ++i)
        {
            for (int j = 0; j <= 8; ++j)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), j, -1, i, balcony);
            }
        }
    }
    
    public static void generateCorridorCrossing(ProtoChunk chunk, NetherFortressGenerator.CorridorCrossing crossing, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 0, 2, 0, 4, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 0, 2, 4, 0, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 4, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
        fillWithOutline(chunk, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, crossing);
    
        for (int i = 0; i <= 4; ++i)
        {
            for (int j = 0; j <= 4; ++j)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, crossing);
            }
        }
    }
    
    public static void generateCorridorNetherWartsRoom(ProtoChunk chunk, NetherFortressGenerator.CorridorNetherWartsRoom wartsRoom, Random random)
    {
        fillWithOutline(chunk, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 0, 5, 0, 12, 13, 12, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        BlockState blockState = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.EAST, true);
        BlockState blockState2 = (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
        BlockState blockState3 = (BlockState) blockState2.with(FenceBlock.WEST, true);
        BlockState blockState4 = (BlockState) blockState2.with(FenceBlock.EAST, true);
    
        int j;
        for (j = 1; j <= 11; j += 2)
        {
            fillWithOutline(chunk, j, 10, 0, j, 11, 0, blockState, blockState, false, wartsRoom);
            fillWithOutline(chunk, j, 10, 12, j, 11, 12, blockState, blockState, false, wartsRoom);
            fillWithOutline(chunk, 0, 10, j, 0, 11, j, blockState2, blockState2, false, wartsRoom);
            fillWithOutline(chunk, 12, 10, j, 12, 11, j, blockState2, blockState2, false, wartsRoom);
            setBlockInStructure(wartsRoom, chunk, Blocks.NETHER_BRICKS.getDefaultState(), j, 13, 0);
            setBlockInStructure(wartsRoom, chunk, Blocks.NETHER_BRICKS.getDefaultState(), j, 13, 12);
            setBlockInStructure(wartsRoom, chunk, Blocks.NETHER_BRICKS.getDefaultState(), 0, 13, j);
            setBlockInStructure(wartsRoom, chunk, Blocks.NETHER_BRICKS.getDefaultState(), 12, 13, j);
            if (j != 11)
            {
                setBlockInStructure(wartsRoom, chunk, blockState, j + 1, 13, 0);
                setBlockInStructure(wartsRoom, chunk, blockState, j + 1, 13, 12);
                setBlockInStructure(wartsRoom, chunk, blockState2, 0, 13, j + 1);
                setBlockInStructure(wartsRoom, chunk, blockState2, 12, 13, j + 1);
            }
        }
    
        setBlockInStructure(wartsRoom, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.EAST, true), 0, 13, 0);
        setBlockInStructure(wartsRoom, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.SOUTH, true)).with(FenceBlock.EAST, true), 0, 13, 12);
        setBlockInStructure(wartsRoom, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.SOUTH, true)).with(FenceBlock.WEST, true), 12, 13, 12);
        setBlockInStructure(wartsRoom, chunk, (BlockState) ((BlockState) Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.WEST, true), 12, 13, 0);
    
        for (j = 3; j <= 9; j += 2)
        {
            fillWithOutline(chunk, 1, 7, j, 1, 8, j, blockState3, blockState3, false, wartsRoom);
            fillWithOutline(chunk, 11, 7, j, 11, 8, j, blockState4, blockState4, false, wartsRoom);
        }
    
        BlockState blockState5 = (BlockState) Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
    
        int k;
        int q;
        for (k = 0; k <= 6; ++k)
        {
            int l = k + 4;
        
            for (q = 5; q <= 7; ++q)
            {
                setBlockInStructure(wartsRoom, chunk, blockState5, q, 5 + k, l);
            }
        
            if (l >= 5 && l <= 8)
            {
                fillWithOutline(chunk, 5, 5, l, 7, k + 4, l, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
            }
            else if (l >= 9 && l <= 10)
            {
                fillWithOutline(chunk, 5, 8, l, 7, k + 4, l, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
            }
        
            if (k >= 1)
            {
                fillWithOutline(chunk, 5, 6 + k, l, 7, 9 + k, l, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, wartsRoom);
            }
        }
    
        for (k = 5; k <= 7; ++k)
        {
            setBlockInStructure(wartsRoom, chunk, blockState5, k, 12, 11);
        }
    
        fillWithOutline(chunk, 5, 6, 7, 5, 7, 7, blockState4, blockState4, false, wartsRoom);
        fillWithOutline(chunk, 7, 6, 7, 7, 7, 7, blockState3, blockState3, false, wartsRoom);
        fillWithOutline(chunk, 5, 13, 12, 7, 13, 12, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 2, 5, 2, 3, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 2, 5, 9, 3, 5, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 2, 5, 4, 2, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 9, 5, 2, 10, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 9, 5, 9, 10, 5, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 10, 5, 4, 10, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        BlockState blockState6 = (BlockState) blockState5.with(StairsBlock.FACING, Direction.EAST);
        BlockState blockState7 = (BlockState) blockState5.with(StairsBlock.FACING, Direction.WEST);
        setBlockInStructure(wartsRoom, chunk, blockState7, 4, 5, 2);
        setBlockInStructure(wartsRoom, chunk, blockState7, 4, 5, 3);
        setBlockInStructure(wartsRoom, chunk, blockState7, 4, 5, 9);
        setBlockInStructure(wartsRoom, chunk, blockState7, 4, 5, 10);
        setBlockInStructure(wartsRoom, chunk, blockState6, 8, 5, 2);
        setBlockInStructure(wartsRoom, chunk, blockState6, 8, 5, 3);
        setBlockInStructure(wartsRoom, chunk, blockState6, 8, 5, 9);
        setBlockInStructure(wartsRoom, chunk, blockState6, 8, 5, 10);
        fillWithOutline(chunk, 3, 4, 4, 4, 4, 8, Blocks.SOUL_SAND.getDefaultState(), Blocks.SOUL_SAND.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 8, 4, 4, 9, 4, 8, Blocks.SOUL_SAND.getDefaultState(), Blocks.SOUL_SAND.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 3, 5, 4, 4, 5, 8, Blocks.NETHER_WART.getDefaultState(), Blocks.NETHER_WART.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 8, 5, 4, 9, 5, 8, Blocks.NETHER_WART.getDefaultState(), Blocks.NETHER_WART.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
        fillWithOutline(chunk, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, wartsRoom);
    
        int r;
        for (q = 4; q <= 8; ++q)
        {
            for (r = 0; r <= 2; ++r)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), q, -1, r, wartsRoom);
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), q, -1, 12 - r, wartsRoom);
            }
        }
    
        for (q = 0; q <= 2; ++q)
        {
            for (r = 4; r <= 8; ++r)
            {
                // method_14936
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), q, -1, r, wartsRoom);
                fillAirAndLiquidDownwards(chunk, Blocks.NETHER_BRICKS.getDefaultState(), 12 - q, -1, r, wartsRoom);
            }
        }
    }
    
    public static void generateBridgeEnd(ProtoChunk chunk, NetherFortressGenerator.BridgeEnd bridgeEnd, Random random)
    {
        Random random2 = new Random((long) ((BridgeEndAccessor) bridgeEnd).getSeed());
    
        int p;
        int q;
        int r;
        for (p = 0; p <= 4; ++p)
        {
            for (q = 3; q <= 4; ++q)
            {
                r = random2.nextInt(8);
                fillWithOutline(chunk, p, q, 0, p, q, r, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeEnd);
            }
        }
    
        p = random2.nextInt(8);
        fillWithOutline(chunk, 0, 5, 0, 0, 5, p, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeEnd);
        p = random2.nextInt(8);
        fillWithOutline(chunk, 4, 5, 0, 4, 5, p, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeEnd);
    
        for (p = 0; p <= 4; ++p)
        {
            q = random2.nextInt(5);
            fillWithOutline(chunk, p, 2, 0, p, 2, q, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeEnd);
        }
    
        for (p = 0; p <= 4; ++p)
        {
            for (q = 0; q <= 1; ++q)
            {
                r = random2.nextInt(3);
                fillWithOutline(chunk, p, q, 0, p, q, r, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, bridgeEnd);
            }
        }
    }
}
