package pt.skyblock.gen;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.ProtoChunk;
import pt.skyblock.PTSkyblockRegistry;

import java.util.Random;

import static pt.skyblock.gen.StructureHelper.*;

public class StrongholdHelper
{
    public static enum EntranceType
    {
        OPENING, WOOD_DOOR, GRATES, IRON_DOOR;
    }
    
    public static EntranceType getRandomEntrance(Random random)
    {
        int i = random.nextInt(5);
        switch (i)
        {
            case 0:
            case 1:
            default:
                return EntranceType.OPENING;
            case 2:
                return EntranceType.WOOD_DOOR;
            case 3:
                return EntranceType.GRATES;
            case 4:
                return EntranceType.IRON_DOOR;
        }
    }
    
    public static void generateEntrance(ProtoChunk chunk, Random random, EntranceType type, int x, int y, int z, StructurePiece piece)
    {
        switch (type)
        {
            case OPENING:
                fillWithOutline(chunk, x, y, z, x + 3 - 1, y + 3 - 1, z, AIR, AIR, false, piece);
                break;
            case WOOD_DOOR:
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x, y, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x, y + 1, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x, y + 2, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x + 1, y + 2, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x + 2, y + 2, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x + 2, y + 1, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x + 2, y, z);
                setBlockInStructure(piece, chunk, Blocks.OAK_DOOR.getDefaultState(), x + 1, y, z);
                setBlockInStructure(piece, chunk, (BlockState) Blocks.OAK_DOOR.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.UPPER), x + 1, y + 1, z);
                break;
            case GRATES:
                setBlockInStructure(piece, chunk, Blocks.CAVE_AIR.getDefaultState(), x + 1, y, z);
                setBlockInStructure(piece, chunk, Blocks.CAVE_AIR.getDefaultState(), x + 1, y + 1, z);
                setBlockInStructure(piece, chunk, (BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true), x, y, z);
                setBlockInStructure(piece, chunk, (BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true), x, y + 1, z);
                setBlockInStructure(piece, chunk, (BlockState) ((BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.EAST, true)).with(PaneBlock.WEST, true), x, y + 2, z);
                setBlockInStructure(piece, chunk, (BlockState) ((BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.EAST, true)).with(PaneBlock.WEST, true), x + 1, y + 2, z);
                setBlockInStructure(piece, chunk, (BlockState) ((BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.EAST, true)).with(PaneBlock.WEST, true), x + 2, y + 2, z);
                setBlockInStructure(piece, chunk, (BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.EAST, true), x + 2, y + 1, z);
                setBlockInStructure(piece, chunk, (BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.EAST, true), x + 2, y, z);
                break;
            case IRON_DOOR:
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x, y, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x, y + 1, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x, y + 2, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x + 1, y + 2, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x + 2, y + 2, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x + 2, y + 1, z);
                setBlockInStructure(piece, chunk, Blocks.STONE_BRICKS.getDefaultState(), x + 2, y, z);
                setBlockInStructure(piece, chunk, Blocks.IRON_DOOR.getDefaultState(), x + 1, y, z);
                setBlockInStructure(piece, chunk, (BlockState) Blocks.IRON_DOOR.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.UPPER), x + 1, y + 1, z);
                setBlockInStructure(piece, chunk, (BlockState) Blocks.STONE_BUTTON.getDefaultState().with(AbstractButtonBlock.FACING, Direction.NORTH), x + 2, y + 1, z + 1);
                setBlockInStructure(piece, chunk, (BlockState) Blocks.STONE_BUTTON.getDefaultState().with(AbstractButtonBlock.FACING, Direction.SOUTH), x + 2, y + 1, z - 1);
        }
    }
    
    public static void generateStrongholdPortalRoom(ProtoChunk chunk, StrongholdGenerator.PortalRoom room, Random random)
    {
        fillWithOutline(chunk, 0, 0, 0, 10, 7, 15, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        generateEntrance(chunk, random, EntranceType.GRATES, 4, 1, 0, room);
        int i = 6;
        fillWithOutline(chunk, 1, i, 1, 1, i, 14, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 9, i, 1, 9, i, 14, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 2, i, 1, 8, i, 2, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 2, i, 14, 8, i, 14, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 1, 1, 1, 2, 1, 4, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 8, 1, 1, 9, 1, 4, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false, room);
        fillWithOutline(chunk, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false, room);
        fillWithOutline(chunk, 3, 1, 8, 7, 1, 12, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false, room);
        BlockState blockState = (BlockState) ((BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true);
        BlockState blockState2 = (BlockState) ((BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true)).with(PaneBlock.EAST, true);
        
        int k;
        for (k = 3; k < 14; k += 2)
        {
            fillWithOutline(chunk, 0, 3, k, 0, 4, k, blockState, blockState, false, room);
            fillWithOutline(chunk, 10, 3, k, 10, 4, k, blockState, blockState, false, room);
        }
        
        for (k = 2; k < 9; k += 2)
        {
            fillWithOutline(chunk, k, 3, 15, k, 4, 15, blockState2, blockState2, false, room);
        }
        
        BlockState blockState3 = (BlockState) Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
        fillWithOutline(chunk, 4, 1, 5, 6, 1, 7, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 4, 2, 6, 6, 2, 7, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        fillWithOutline(chunk, 4, 3, 7, 6, 3, 7, false, random, PTSkyblockRegistry.stoneBrickRandomizer, room);
        
        for (int l = 4; l <= 6; ++l)
        {
            setBlockInStructure(room, chunk, blockState3, l, 1, 4);
            setBlockInStructure(room, chunk, blockState3, l, 2, 5);
            setBlockInStructure(room, chunk, blockState3, l, 3, 6);
        }
        
        BlockState northFrame = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
        BlockState southFrame = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
        BlockState eastFrame = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
        BlockState westFrame = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);
        boolean completelyFilled = true;
        boolean[] framesFilled = new boolean[12];
        
        for (int l = 0; l < framesFilled.length; ++l)
        {
            framesFilled[l] = random.nextFloat() > 0.9F;
            completelyFilled &= framesFilled[l];
        }
        setBlockInStructure(room, chunk, northFrame.with(EndPortalFrameBlock.EYE, framesFilled[0]), 4, 3, 8);
        setBlockInStructure(room, chunk, northFrame.with(EndPortalFrameBlock.EYE, framesFilled[1]), 5, 3, 8);
        setBlockInStructure(room, chunk, northFrame.with(EndPortalFrameBlock.EYE, framesFilled[2]), 6, 3, 8);
        setBlockInStructure(room, chunk, southFrame.with(EndPortalFrameBlock.EYE, framesFilled[3]), 4, 3, 12);
        setBlockInStructure(room, chunk, southFrame.with(EndPortalFrameBlock.EYE, framesFilled[4]), 5, 3, 12);
        setBlockInStructure(room, chunk, southFrame.with(EndPortalFrameBlock.EYE, framesFilled[5]), 6, 3, 12);
        setBlockInStructure(room, chunk, eastFrame.with(EndPortalFrameBlock.EYE, framesFilled[6]), 3, 3, 9);
        setBlockInStructure(room, chunk, eastFrame.with(EndPortalFrameBlock.EYE, framesFilled[7]), 3, 3, 10);
        setBlockInStructure(room, chunk, eastFrame.with(EndPortalFrameBlock.EYE, framesFilled[8]), 3, 3, 11);
        setBlockInStructure(room, chunk, westFrame.with(EndPortalFrameBlock.EYE, framesFilled[9]), 7, 3, 9);
        setBlockInStructure(room, chunk, westFrame.with(EndPortalFrameBlock.EYE, framesFilled[10]), 7, 3, 10);
        setBlockInStructure(room, chunk, westFrame.with(EndPortalFrameBlock.EYE, framesFilled[11]), 7, 3, 11);
        if (completelyFilled)
        {
            BlockState portal = Blocks.END_PORTAL.getDefaultState();
            setBlockInStructure(room, chunk, portal, 4, 3, 9);
            setBlockInStructure(room, chunk, portal, 5, 3, 9);
            setBlockInStructure(room, chunk, portal, 6, 3, 9);
            setBlockInStructure(room, chunk, portal, 4, 3, 10);
            setBlockInStructure(room, chunk, portal, 5, 3, 10);
            setBlockInStructure(room, chunk, portal, 6, 3, 10);
            setBlockInStructure(room, chunk, portal, 4, 3, 11);
            setBlockInStructure(room, chunk, portal, 5, 3, 11);
            setBlockInStructure(room, chunk, portal, 6, 3, 11);
        }
        BlockPos spawnerPos = getBlockInStructurePiece(room, 5, 3, 6);
        setBlockInChunk(chunk, spawnerPos, Blocks.SPAWNER.getDefaultState());
        CompoundTag spawnerTag = new CompoundTag();
        spawnerTag.putString("id", "minecraft:mob_spawner");
        ListTag spawnPotentials = new ListTag();
        spawnerTag.put("SpawnPotentials", spawnPotentials);
        CompoundTag spawnEntry = new CompoundTag();
        spawnPotentials.addTag(0, spawnEntry);
        CompoundTag entity = new CompoundTag();
        spawnEntry.put("Entity", entity);
        entity.putString("id", "minecraft:silverfish");
        spawnEntry.putInt("Weight", 1);
        spawnerTag.put("SpawnData", entity.copy());
        setBlockEntityInChunk(chunk, spawnerPos, spawnerTag);
    }
}
