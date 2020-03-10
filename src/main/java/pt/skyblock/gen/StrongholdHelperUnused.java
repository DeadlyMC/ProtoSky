package pt.skyblock.gen;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.loot.LootTables;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.ProtoChunk;
import pt.skyblock.PTSkyblockRegistry;
import pt.skyblock.mixins.*;
import pt.skyblock.util.IChestCorridor;

import java.util.Random;

import static pt.skyblock.gen.StrongholdHelper.generateEntrance;
import static pt.skyblock.gen.StrongholdHelper.getRandomEntrance;
import static pt.skyblock.gen.StructureHelper.*;

// Redundant class - Moved here in case is required in the future
public class StrongholdHelperUnused
{
    public static void generateStrongholdCorridor(ProtoChunk chunk, StrongholdGenerator.Corridor room, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 4, 4, 6, true, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, getRandomEntrance(random), 1, 1, 0, room);
        generateEntrance(chunk, random, StrongholdHelper.EntranceType.OPENING, 1, 1, 6, room);
        BlockState blockState = (BlockState) Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.EAST);
        BlockState blockState2 = (BlockState) Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.WEST);
        addBlockWithRandomThreshold(chunk, random, 0.1F, 1, 2, 1, blockState, room);
        addBlockWithRandomThreshold(chunk, random, 0.1F, 3, 2, 1, blockState2, room);
        addBlockWithRandomThreshold(chunk, random, 0.1F, 1, 2, 5, blockState, room);
        addBlockWithRandomThreshold(chunk, random, 0.1F, 3, 2, 5, blockState2, room);
        if (((CorridorAccessor) room).getLeftExitExists())
        {
            fillWithOutline(chunk, 0, 1, 2, 0, 3, 4, AIR, AIR, false, room);
        }
        
        if (((CorridorAccessor) room).getRightExitExixts())
        {
            fillWithOutline(chunk, 4, 1, 2, 4, 3, 4, AIR, AIR, false, room);
        }
    }
    
    public static void generateStrongholdPrisonHall(ProtoChunk chunk, StrongholdGenerator.PrisonHall room, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 8, 4, 10, true, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, getRandomEntrance(random), 1, 1, 0, room);
        fillWithOutline(chunk, 1, 1, 10, 3, 3, 10, AIR, AIR, false, room);
        fillWithOutline(chunk, 4, 1, 1, 4, 3, 1, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 4, 1, 3, 4, 3, 3, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 4, 1, 7, 4, 3, 7, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 4, 1, 9, 4, 3, 9, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        
        for(int i = 1; i <= 3; ++i) {
            setBlockInStructure(room, chunk, (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true), 4, i, 4);
            setBlockInStructure(room, chunk, (BlockState)((BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true)).with(PaneBlock.EAST, true), 4, i, 5);
            setBlockInStructure(room, chunk, (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true), 4, i, 6);
            setBlockInStructure(room, chunk, (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true)).with(PaneBlock.EAST, true), 5, i, 5);
            setBlockInStructure(room, chunk, (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true)).with(PaneBlock.EAST, true), 6, i, 5);
            setBlockInStructure(room, chunk, (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true)).with(PaneBlock.EAST, true), 7, i, 5);
        }
        
        setBlockInStructure(room, chunk, (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true), 4, 3, 2);
        setBlockInStructure(room, chunk, (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true), 4, 3, 8);
        BlockState blockState = (BlockState)Blocks.IRON_DOOR.getDefaultState().with(DoorBlock.FACING, Direction.WEST);
        BlockState blockState2 = (BlockState)((BlockState)Blocks.IRON_DOOR.getDefaultState().with(DoorBlock.FACING, Direction.WEST)).with(DoorBlock.HALF, DoubleBlockHalf.UPPER);
        setBlockInStructure(room, chunk, blockState, 4, 1, 2);
        setBlockInStructure(room, chunk, blockState2, 4, 2, 2);
        setBlockInStructure(room, chunk, blockState, 4, 1, 8);
        setBlockInStructure(room, chunk, blockState2, 4, 2, 8);
    }
    
    public static void generateStrongholdLeftTurn(ProtoChunk chunk, StrongholdGenerator.LeftTurn room, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 4, 4, 4, true, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, getRandomEntrance(random), 1, 1, 0, room);
        Direction direction = room.getFacing();
        if (direction != Direction.NORTH && direction != Direction.EAST)
        {
            fillWithOutline(chunk, 4, 1, 1, 4, 3, 3, AIR, AIR, false, room);
        }
        else
        {
            fillWithOutline(chunk, 0, 1, 1, 0, 3, 3, AIR, AIR, false, room);
        }
    }
    
    public static void generateStrongholdRightTurn(ProtoChunk chunk, StrongholdGenerator.RightTurn room, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 4, 4, 4, true, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, getRandomEntrance(random), 1, 1, 0, room);
        Direction direction = room.getFacing();
        if (direction != Direction.NORTH && direction != Direction.EAST)
        {
            fillWithOutline(chunk, 0, 1, 1, 0, 3, 3, AIR, AIR, false, room);
        }
        else
        {
            fillWithOutline(chunk, 4, 1, 1, 4, 3, 3, AIR, AIR, false, room);
        }
    }
    
    public static void generateStrongholdSquareRoom(ProtoChunk chunk, StrongholdGenerator.SquareRoom room, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 10, 6, 10, true, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, getRandomEntrance(random), 4, 1, 0, room);
        fillWithOutline(chunk, 4, 1, 10, 6, 3, 10, AIR, AIR, false, room);
        fillWithOutline(chunk, 0, 1, 4, 0, 3, 6, AIR, AIR, false, room);
        fillWithOutline(chunk, 10, 1, 4, 10, 3, 6, AIR, AIR, false, room);
        int m;
        switch (((SquareRoomAccessor) room).getRoomType())
        {
            case 0:
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 5, 1, 5);
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 5, 2, 5);
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 5, 3, 5);
                setBlockInStructure(room, chunk, (BlockState) Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.WEST), 4, 3, 5);
                setBlockInStructure(room, chunk, (BlockState) Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.EAST), 6, 3, 5);
                setBlockInStructure(room, chunk, (BlockState) Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.SOUTH), 5, 3, 4);
                setBlockInStructure(room, chunk, (BlockState) Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.NORTH), 5, 3, 6);
                setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 4, 1, 4);
                setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 4, 1, 5);
                setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 4, 1, 6);
                setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 6, 1, 4);
                setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 6, 1, 5);
                setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 6, 1, 6);
                setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 5, 1, 4);
                setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 5, 1, 6);
                break;
            case 1:
                for (m = 0; m < 5; ++m)
                {
                    setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 3, 1, 3 + m);
                    setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 7, 1, 3 + m);
                    setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 3 + m, 1, 3);
                    setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 3 + m, 1, 7);
                }
                
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 5, 1, 5);
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 5, 2, 5);
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 5, 3, 5);
                setBlockInStructure(room, chunk, Blocks.WATER.getDefaultState(), 5, 4, 5);
                break;
            case 2:
                for (m = 1; m <= 9; ++m)
                {
                    setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 1, 3, m);
                    setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 9, 3, m);
                }
                
                for (m = 1; m <= 9; ++m)
                {
                    setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), m, 3, 1);
                    setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), m, 3, 9);
                }
                
                setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 5, 1, 4);
                setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 5, 1, 6);
                setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 5, 3, 4);
                setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 5, 3, 6);
                setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 4, 1, 5);
                setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 6, 1, 5);
                setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 4, 3, 5);
                setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 6, 3, 5);
                
                for (m = 1; m <= 3; ++m)
                {
                    setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 4, m, 4);
                    setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 6, m, 4);
                    setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 4, m, 6);
                    setBlockInStructure(room, chunk, Blocks.COBBLESTONE.getDefaultState(), 6, m, 6);
                }
                
                setBlockInStructure(room, chunk, Blocks.TORCH.getDefaultState(), 5, 3, 5);
                
                for (m = 2; m <= 8; ++m)
                {
                    setBlockInStructure(room, chunk, Blocks.OAK_PLANKS.getDefaultState(), 2, 3, m);
                    setBlockInStructure(room, chunk, Blocks.OAK_PLANKS.getDefaultState(), 3, 3, m);
                    if (m <= 3 || m >= 7)
                    {
                        setBlockInStructure(room, chunk, Blocks.OAK_PLANKS.getDefaultState(), 4, 3, m);
                        setBlockInStructure(room, chunk, Blocks.OAK_PLANKS.getDefaultState(), 5, 3, m);
                        setBlockInStructure(room, chunk, Blocks.OAK_PLANKS.getDefaultState(), 6, 3, m);
                    }
                    
                    setBlockInStructure(room, chunk, Blocks.OAK_PLANKS.getDefaultState(), 7, 3, m);
                    setBlockInStructure(room, chunk, Blocks.OAK_PLANKS.getDefaultState(), 8, 3, m);
                }
                
                BlockState blockState = (BlockState) Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, Direction.WEST);
                setBlockInStructure(room, chunk, blockState, 9, 1, 3);
                setBlockInStructure(room, chunk, blockState, 9, 2, 3);
                setBlockInStructure(room, chunk, blockState, 9, 3, 3);
                addChest(chunk, random, 3, 4, 8, LootTables.STRONGHOLD_CROSSING_CHEST, (BlockState)null, room);
        }
    }
    
    public static void generateStrongholdStairs(ProtoChunk chunk, StrongholdGenerator.Stairs room, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 4, 10, 7, true, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, getRandomEntrance(random), 1, 7, 0, room);
        generateEntrance(chunk, random, StrongholdHelper.EntranceType.OPENING, 1, 1, 7, room);
        BlockState blockState = (BlockState) Blocks.COBBLESTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);
        
        for (int i = 0; i < 6; ++i)
        {
            setBlockInStructure(room, chunk, blockState, 1, 6 - i, 1 + i);
            setBlockInStructure(room, chunk, blockState, 2, 6 - i, 1 + i);
            setBlockInStructure(room, chunk, blockState, 3, 6 - i, 1 + i);
            if (i < 5)
            {
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 1, 5 - i, 1 + i);
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 2, 5 - i, 1 + i);
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 3, 5 - i, 1 + i);
            }
        }
    }
    
    public static void generateStrongholdSpiralStaircase(ProtoChunk chunk, StrongholdGenerator.SpiralStaircase room, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 4, 10, 4, true, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, getRandomEntrance(random), 1, 7, 0, room);
        generateEntrance(chunk, random, StrongholdHelper.EntranceType.OPENING, 1, 1, 4, room);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 2, 6, 1);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 1, 5, 1);
        setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 1, 6, 1);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 1, 5, 2);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 1, 4, 3);
        setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 1, 5, 3);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 2, 4, 3);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 3, 3, 3);
        setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 3, 4, 3);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 3, 3, 2);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 3, 2, 1);
        setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 3, 3, 1);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 2, 2, 1);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 1, 1, 1);
        setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 1, 2, 1);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 1, 1, 2);
        setBlockInStructure(room, chunk, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), 1, 1, 3);
    }
    
    public static void generateStrongholdFiveWayCrossing(ProtoChunk chunk, StrongholdGenerator.FiveWayCrossing room, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 9, 8, 10, true, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, getRandomEntrance(random), 4, 3, 0, room);
        if (((FiveWayCrossingAccessor) room).getLowerLeftExists())
        {
            fillWithOutline(chunk, 0, 3, 1, 0, 5, 3, AIR, AIR, false, room);
        }
        
        if (((FiveWayCrossingAccessor) room).getLowerRightExists())
        {
            fillWithOutline(chunk, 9, 3, 1, 9, 5, 3, AIR, AIR, false, room);
        }
        
        if (((FiveWayCrossingAccessor) room).getUpperLeftExists())
        {
            fillWithOutline(chunk, 0, 5, 7, 0, 7, 9, AIR, AIR, false, room);
        }
        
        if (((FiveWayCrossingAccessor) room).getUpperRightExists())
        {
            fillWithOutline(chunk, 9, 5, 7, 9, 7, 9, AIR, AIR, false, room);
        }
        
        fillWithOutline(chunk, 5, 1, 10, 7, 3, 10, AIR, AIR, false, room);
        fillWithOutline(chunk, 1, 2, 1, 8, 2, 6, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 4, 1, 5, 4, 4, 9, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 8, 1, 5, 8, 4, 9, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 1, 4, 7, 3, 4, 9, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 1, 3, 5, 3, 3, 6, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 1, 3, 4, 3, 3, 4, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), Blocks.SMOOTH_STONE_SLAB.getDefaultState(), false, room);
        fillWithOutline(chunk, 1, 4, 6, 3, 4, 6, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), Blocks.SMOOTH_STONE_SLAB.getDefaultState(), false, room);
        fillWithOutline(chunk, 5, 1, 7, 7, 1, 8, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 5, 1, 9, 7, 1, 9, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), Blocks.SMOOTH_STONE_SLAB.getDefaultState(), false, room);
        fillWithOutline(chunk, 5, 2, 7, 7, 2, 7, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), Blocks.SMOOTH_STONE_SLAB.getDefaultState(), false, room);
        fillWithOutline(chunk, 4, 5, 7, 4, 5, 9, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), Blocks.SMOOTH_STONE_SLAB.getDefaultState(), false, room);
        fillWithOutline(chunk, 8, 5, 7, 8, 5, 9, Blocks.SMOOTH_STONE_SLAB.getDefaultState(), Blocks.SMOOTH_STONE_SLAB.getDefaultState(), false, room);
        fillWithOutline(chunk, 5, 5, 7, 7, 5, 9, (BlockState) Blocks.SMOOTH_STONE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.DOUBLE), (BlockState) Blocks.SMOOTH_STONE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.DOUBLE), false, room);
        setBlockInStructure(room, chunk, (BlockState) Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.SOUTH), 6, 5, 6);
    }
    
    public static void generateStrongholdChestCorridor(ProtoChunk chunk, StrongholdGenerator.ChestCorridor room, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 4, 4, 6, true, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, getRandomEntrance(random), 1, 1, 0, room);
        generateEntrance(chunk, random, StrongholdHelper.EntranceType.OPENING, 1, 1, 6, room);
        fillWithOutline(chunk, 3, 1, 2, 3, 1, 4, Blocks.STONE_BRICKS.getDefaultState(), Blocks.STONE_BRICKS.getDefaultState(), false, room);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 1, 1);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 1, 5);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 2, 2);
        setBlockInStructure(room, chunk, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 2, 4);
        
        for (int i = 2; i <= 4; ++i)
        {
            setBlockInStructure(room, chunk, Blocks.STONE_BRICK_SLAB.getDefaultState(), 2, 1, i);
        }
        
        StructurePieceAccessor access = (StructurePieceAccessor) room;
        if (!((IChestCorridor) room).getChestGenerated() && room.getBoundingBox().contains(new BlockPos(access.invokeApplyXTransform(3, 3), access.invokeApplyYTransform(2), access.invokeApplyZTransform(3, 3))))
        {
            ((IChestCorridor) room).setChestGenerated(true);
            addChest(chunk, random, 3, 2, 3, LootTables.STRONGHOLD_CORRIDOR_CHEST, (BlockState)null, room);
        }
    }
    
    public static void generateStrongholdLibrary(ProtoChunk chunk, StrongholdGenerator.Library room, Random random)
    {
        int i = 11;
        if (!((LibraryAccessor) room).getTall())
        {
            i = 6;
        }
        
        fillWithOutline(chunk, 0, 0, 0, 13, i - 1, 14, true, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, getRandomEntrance(random), 4, 1, 0, room);
        fillWithOutlineUnderSealevel(chunk, random, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.COBWEB.getDefaultState(), Blocks.COBWEB.getDefaultState(), false, false, room);
        
        int l;
        for (l = 1; l <= 13; ++l)
        {
            if ((l - 1) % 4 == 0)
            {
                fillWithOutline(chunk, 1, 1, l, 1, 4, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false, room);
                fillWithOutline(chunk, 12, 1, l, 12, 4, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false, room);
                setBlockInStructure(room, chunk, (BlockState) Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.EAST), 2, 3, l);
                setBlockInStructure(room, chunk, (BlockState) Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.WEST), 11, 3, l);
                if (((LibraryAccessor) room).getTall())
                {
                    fillWithOutline(chunk, 1, 6, l, 1, 9, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false, room);
                    fillWithOutline(chunk, 12, 6, l, 12, 9, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false, room);
                }
            }
            else
            {
                fillWithOutline(chunk, 1, 1, l, 1, 4, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false, room);
                fillWithOutline(chunk, 12, 1, l, 12, 4, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false, room);
                if (((LibraryAccessor) room).getTall())
                {
                    fillWithOutline(chunk, 1, 6, l, 1, 9, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false, room);
                    fillWithOutline(chunk, 12, 6, l, 12, 9, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false, room);
                }
            }
        }
        
        for (l = 3; l < 12; l += 2)
        {
            fillWithOutline(chunk, 3, 1, l, 4, 3, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false, room);
            fillWithOutline(chunk, 6, 1, l, 7, 3, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false, room);
            fillWithOutline(chunk, 9, 1, l, 10, 3, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false, room);
        }
        
        if (((LibraryAccessor) room).getTall())
        {
            fillWithOutline(chunk, 1, 5, 1, 3, 5, 13, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false, room);
            fillWithOutline(chunk, 10, 5, 1, 12, 5, 13, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false, room);
            fillWithOutline(chunk, 4, 5, 1, 9, 5, 2, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false, room);
            fillWithOutline(chunk, 4, 5, 12, 9, 5, 13, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false, room);
            setBlockInStructure(room, chunk, Blocks.OAK_PLANKS.getDefaultState(), 9, 5, 11);
            setBlockInStructure(room, chunk, Blocks.OAK_PLANKS.getDefaultState(), 8, 5, 11);
            setBlockInStructure(room, chunk, Blocks.OAK_PLANKS.getDefaultState(), 9, 5, 10);
            BlockState blockState = (BlockState) ((BlockState) Blocks.OAK_FENCE.getDefaultState().with(FenceBlock.WEST, true)).with(FenceBlock.EAST, true);
            BlockState blockState2 = (BlockState) ((BlockState) Blocks.OAK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.SOUTH, true);
            fillWithOutline(chunk, 3, 6, 3, 3, 6, 11, blockState2, blockState2, false, room);
            fillWithOutline(chunk, 10, 6, 3, 10, 6, 9, blockState2, blockState2, false, room);
            fillWithOutline(chunk, 4, 6, 2, 9, 6, 2, blockState, blockState, false, room);
            fillWithOutline(chunk, 4, 6, 12, 7, 6, 12, blockState, blockState, false, room);
            setBlockInStructure(room, chunk, (BlockState) ((BlockState) Blocks.OAK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.EAST, true), 3, 6, 2);
            setBlockInStructure(room, chunk, (BlockState) ((BlockState) Blocks.OAK_FENCE.getDefaultState().with(FenceBlock.SOUTH, true)).with(FenceBlock.EAST, true), 3, 6, 12);
            setBlockInStructure(room, chunk, (BlockState) ((BlockState) Blocks.OAK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.WEST, true), 10, 6, 2);
            
            for (int n = 0; n <= 2; ++n)
            {
                setBlockInStructure(room, chunk, (BlockState) ((BlockState) Blocks.OAK_FENCE.getDefaultState().with(FenceBlock.SOUTH, true)).with(FenceBlock.WEST, true), 8 + n, 6, 12 - n);
                if (n != 2)
                {
                    setBlockInStructure(room, chunk, (BlockState) ((BlockState) Blocks.OAK_FENCE.getDefaultState().with(FenceBlock.NORTH, true)).with(FenceBlock.EAST, true), 8 + n, 6, 11 - n);
                }
            }
            
            BlockState blockState3 = (BlockState) Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, Direction.SOUTH);
            setBlockInStructure(room, chunk, blockState3, 10, 1, 13);
            setBlockInStructure(room, chunk, blockState3, 10, 2, 13);
            setBlockInStructure(room, chunk, blockState3, 10, 3, 13);
            setBlockInStructure(room, chunk, blockState3, 10, 4, 13);
            setBlockInStructure(room, chunk, blockState3, 10, 5, 13);
            setBlockInStructure(room, chunk, blockState3, 10, 6, 13);
            setBlockInStructure(room, chunk, blockState3, 10, 7, 13);
            BlockState blockState4 = (BlockState) Blocks.OAK_FENCE.getDefaultState().with(FenceBlock.EAST, true);
            setBlockInStructure(room, chunk, blockState4, 6, 9, 7);
            BlockState blockState5 = (BlockState) Blocks.OAK_FENCE.getDefaultState().with(FenceBlock.WEST, true);
            setBlockInStructure(room, chunk, blockState5, 7, 9, 7);
            setBlockInStructure(room, chunk, blockState4, 6, 8, 7);
            setBlockInStructure(room, chunk, blockState5, 7, 8, 7);
            BlockState blockState6 = (BlockState) ((BlockState) blockState2.with(FenceBlock.WEST, true)).with(FenceBlock.EAST, true);
            setBlockInStructure(room, chunk, blockState6, 6, 7, 7);
            setBlockInStructure(room, chunk, blockState6, 7, 7, 7);
            setBlockInStructure(room, chunk, blockState4, 5, 7, 7);
            setBlockInStructure(room, chunk, blockState5, 8, 7, 7);
            setBlockInStructure(room, chunk, (BlockState) blockState4.with(FenceBlock.NORTH, true), 6, 7, 6);
            setBlockInStructure(room, chunk, (BlockState) blockState4.with(FenceBlock.SOUTH, true), 6, 7, 8);
            setBlockInStructure(room, chunk, (BlockState) blockState5.with(FenceBlock.NORTH, true), 7, 7, 6);
            setBlockInStructure(room, chunk, (BlockState) blockState5.with(FenceBlock.SOUTH, true), 7, 7, 8);
            BlockState blockState7 = Blocks.TORCH.getDefaultState();
            setBlockInStructure(room, chunk, blockState7, 5, 8, 7);
            setBlockInStructure(room, chunk, blockState7, 8, 8, 7);
            setBlockInStructure(room, chunk, blockState7, 6, 8, 6);
            setBlockInStructure(room, chunk, blockState7, 6, 8, 8);
            setBlockInStructure(room, chunk, blockState7, 7, 8, 6);
            setBlockInStructure(room, chunk, blockState7, 7, 8, 8);
        }
        
        addChest(chunk, random, 3, 3, 5, LootTables.STRONGHOLD_LIBRARY_CHEST, (BlockState)null, room);
        if (((LibraryAccessor) room).getTall())
        {
            setBlockInStructure(room, chunk, AIR, 12, 9, 1);
            addChest(chunk, random, 12, 8, 1, LootTables.STRONGHOLD_LIBRARY_CHEST, (BlockState)null, room);
        }
    }
    
    public static void generateStrongholdSmallCorridor(ProtoChunk chunk, StrongholdGenerator.SmallCorridor room, Random random)
    {
        for (int i = 0; i < ((SmallCorridorAccessor) room).getLength(); ++i)
        {
            setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 0, 0, i);
            setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 1, 0, i);
            setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 2, 0, i);
            setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 3, 0, i);
            setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 4, 0, i);
            
            for (int j = 1; j <= 3; ++j)
            {
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 0, j, i);
                setBlockInStructure(room, chunk, Blocks.CAVE_AIR.getDefaultState(), 1, j, i);
                setBlockInStructure(room, chunk, Blocks.CAVE_AIR.getDefaultState(), 2, j, i);
                setBlockInStructure(room, chunk, Blocks.CAVE_AIR.getDefaultState(), 3, j, i);
                setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 4, j, i);
            }
            
            setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 0, 4, i);
            setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 1, 4, i);
            setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 2, 4, i);
            setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 3, 4, i);
            setBlockInStructure(room, chunk, Blocks.STONE_BRICKS.getDefaultState(), 4, 4, i);
        }
    }
}
