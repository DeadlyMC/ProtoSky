package pt.skyblock.gen;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.NetherFortressGenerator;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.world.BlockView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.ProtoChunk;
import pt.skyblock.mixins.*;

import java.util.Random;

public class StructureHelper
{
    public static final BlockState AIR = Blocks.CAVE_AIR.getDefaultState();
    
    public static BlockPos getBlockInStructurePiece(StructurePiece piece, int x, int y, int z)
    {
        StructurePieceAccessor access = (StructurePieceAccessor) piece;
        return new BlockPos(access.invokeApplyXTransform(x, z), access.invokeApplyYTransform(y), access.invokeApplyZTransform(x, z));
    }
    
    public static BlockState getBlockAt(BlockView blockView, int x, int y, int z, StructurePiece piece)
    {
        StructurePieceAccessor access = (StructurePieceAccessor) piece;
        int i = access.invokeApplyXTransform(x, z);
        int j = access.invokeApplyYTransform(y);
        int k = access.invokeApplyZTransform(x, z);
        BlockPos blockPos = new BlockPos(i, j, k);
        return !piece.getBoundingBox().contains(blockPos) ? Blocks.AIR.getDefaultState() : blockView.getBlockState(blockPos);
    }
    
    public static boolean isUnderSeaLevel(WorldView worldView, int x, int z, int y, StructurePiece piece)
    {
        StructurePieceAccessor access = (StructurePieceAccessor) piece;
        int i = access.invokeApplyXTransform(x, y);
        int j = access.invokeApplyYTransform(z + 1);
        int k = access.invokeApplyZTransform(x, y);
        BlockPos blockPos = new BlockPos(i, j, k);
        if (!piece.getBoundingBox().contains(blockPos))
        {
            return false;
        }
        else
        {
            return j < worldView.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, i, k);
        }
    }
    
    public static void fillWithOutline(ProtoChunk chunk, int i, int j, int k, int l, int m, int n, BlockState blockState, BlockState inside, boolean bl, StructurePiece piece)
    {
        for (int o = j; o <= m; ++o)
        {
            for (int p = i; p <= l; ++p)
            {
                for (int q = k; q <= n; ++q)
                {
                    if (!bl || !getBlockAt(chunk, p, o, q, piece).isAir())
                    {
                        if (o != j && o != m && p != i && p != l && q != k && q != n)
                        {
                            setBlockInStructure(piece, chunk, inside, p, o, q);
                        }
                        else
                        {
                            setBlockInStructure(piece, chunk, blockState, p, o, q);
                        }
                    }
                }
            }
        }
        
    }
    
    public static void fillWithOutline(ProtoChunk chunk, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean replaceBlocks, Random random, StructurePiece.BlockRandomizer blockRandomizer, StructurePiece piece)
    {
        for (int i = minY; i <= maxY; ++i)
        {
            for (int j = minX; j <= maxX; ++j)
            {
                for (int k = minZ; k <= maxZ; ++k)
                {
                    if (!replaceBlocks || !getBlockAt(chunk, j, i, k, piece).isAir())
                    {
                        blockRandomizer.setBlock(random, j, i, k, i == minY || i == maxY || j == minX || j == maxX || k == minZ || k == maxZ);
                        setBlockInStructure(piece, chunk, blockRandomizer.getBlock(), j, i, k);
                    }
                }
            }
        }
        
    }
    
    public static void fillWithOutlineUnderSealevel(ProtoChunk chunk, Random random, float f, int i, int j, int k, int l, int m, int n, BlockState blockState, BlockState blockState2, boolean bl, boolean bl2, StructurePiece piece)
    {
        for (int o = j; o <= m; ++o)
        {
            for (int p = i; p <= l; ++p)
            {
                for (int q = k; q <= n; ++q)
                {
                    if (random.nextFloat() <= f && (!bl || !getBlockAt(chunk, p, o, q, piece).isAir()) && (!bl2 || isUnderSeaLevel((WorldView) chunk, p, o, q, piece)))
                    {
                        if (o != j && o != m && p != i && p != l && q != k && q != n)
                        {
                            setBlockInStructure(piece, chunk, blockState2, p, o, q);
                        }
                        else
                        {
                            setBlockInStructure(piece, chunk, blockState, p, o, q);
                        }
                    }
                }
            }
        }
    }
    
    public static boolean addChest(ProtoChunk chunk, Random random, int x, int y, int z, Identifier lootTableId, /*@Nullable*/ BlockState block, StructurePiece piece)
    {
        StructurePieceAccessor access = (StructurePieceAccessor) piece;
        BlockPos pos = new BlockPos(access.invokeApplyXTransform(x, z), access.invokeApplyYTransform(y), access.invokeApplyZTransform(x, z));
        if (piece.getBoundingBox().contains(pos) && chunk.getBlockState(pos).getBlock() != Blocks.CHEST)
        {
            if (block == null)
            {
                block = StructurePiece.method_14916(chunk, pos, Blocks.CHEST.getDefaultState());
            }
            
            setBlockInChunk(chunk, pos, block);
            BlockEntity blockEntity = chunk.getBlockEntity(pos);
            if (blockEntity instanceof ChestBlockEntity)
            {
                ((ChestBlockEntity) blockEntity).setLootTable(lootTableId, random.nextLong());
            }
            
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static void addBlockWithRandomThreshold(ProtoChunk chunk, Random random, float threshold, int x, int y, int z, BlockState blockState, StructurePiece piece)
    {
        if (random.nextFloat() < threshold)
        {
            setBlockInStructure(piece, chunk, blockState, x, y, z);
        }
    }
    
    public static void setBlockInStructure(StructurePiece piece, ProtoChunk chunk, BlockState state, int x, int y, int z)
    {
        StructurePieceAccessor access = (StructurePieceAccessor) piece;
        BlockPos pos = getBlockInStructurePiece(piece, x, y, z);
        if (piece.getBoundingBox().contains(pos))
        {
            BlockMirror mirror = access.getMirror();
            if (mirror != BlockMirror.NONE)
                state = state.mirror(mirror);
            BlockRotation rotation = piece.getRotation();
            if (rotation != BlockRotation.NONE)
                state = state.rotate(rotation);
            
            setBlockInChunk(chunk, pos, state);
        }
    }
    
    public static void setBlockInChunk(ProtoChunk chunk, BlockPos pos, BlockState state)
    {
        if (chunk.getPos().equals(new ChunkPos(pos)))
        {
            chunk.setBlockState(pos, state, false);
        }
    }
    
    public static void setBlockEntityInChunk(ProtoChunk chunk, BlockPos pos, CompoundTag tag)
    {
        if (chunk.getPos().equals(new ChunkPos(pos)))
        {
            tag.putInt("x", pos.getX());
            tag.putInt("y", pos.getY());
            tag.putInt("z", pos.getZ());
            System.out.println(tag);
            chunk.addPendingBlockEntityTag(tag);
        }
    }
    
    public static void fillAirAndLiquidDownwards(ProtoChunk chunk, BlockState blockState, int x, int y, int z, StructurePiece piece)
    {
        StructurePieceAccessor access = (StructurePieceAccessor) piece;
        int i = access.invokeApplyXTransform(x, z);
        int j = access.invokeApplyYTransform(y);
        int k = access.invokeApplyZTransform(x, z);
        if (piece.getBoundingBox().contains(new BlockPos(i, j, k)))
        {
            while ((chunk.getBlockState(new BlockPos(i, j, k)).isAir() || chunk.getBlockState(new BlockPos(i, j, k)).getMaterial().isLiquid()) && j > 1)
            {
                setBlockInChunk(chunk, new BlockPos(i, j, k), blockState);
                --j;
            }
        }
    }
    
    public static void processNetherFortress(ProtoChunk chunk, IWorld world)
    {
        for (long startPosLong : chunk.getStructureReferences("Fortress"))
        {
            ChunkPos startPos = new ChunkPos(startPosLong);
            ProtoChunk startChunk = (ProtoChunk) world.getChunk(startPos.x, startPos.z, ChunkStatus.STRUCTURE_STARTS);
            StructureStart fortress = startChunk.getStructureStart("Fortress");
            ChunkPos pos = chunk.getPos();
            if (fortress != null && fortress.getBoundingBox().intersectsXZ(pos.getStartX(), pos.getStartZ(), pos.getEndX(), pos.getEndZ()))
            {
                for (StructurePiece piece : fortress.getChildren())
                {
                    if (piece.getBoundingBox().intersectsXZ(pos.getStartX(), pos.getStartZ(), pos.getEndX(), pos.getEndZ()))
                    {
                        if (piece instanceof NetherFortressGenerator.Bridge)
                            FortressHelper.generateBridge(chunk, (NetherFortressGenerator.Bridge) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.BridgeCrossing)
                            FortressHelper.generateBridgeCrossing(chunk, (NetherFortressGenerator.BridgeCrossing) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.BridgeSmallCrossing)
                            FortressHelper.generateBridgeSmallCrossing(chunk, (NetherFortressGenerator.BridgeSmallCrossing) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.BridgeStairs)
                            FortressHelper.generateBridgeStairs(chunk, (NetherFortressGenerator.BridgeStairs) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.BridgePlatform)
                            FortressHelper.generateBridgePlatform(chunk, (NetherFortressGenerator.BridgePlatform) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.CorridorExit)
                            FortressHelper.generateCorridorExit(chunk, (NetherFortressGenerator.CorridorExit) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.SmallCorridor)
                            FortressHelper.generateSmallCorridor(chunk, (NetherFortressGenerator.SmallCorridor) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.CorridorRightTurn)
                            FortressHelper.generateCorridorRightTurn(chunk, (NetherFortressGenerator.CorridorRightTurn) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.CorridorLeftTurn)
                            FortressHelper.generateCorridorLeftTurn(chunk, (NetherFortressGenerator.CorridorLeftTurn) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.CorridorStairs)
                            FortressHelper.generateCorridorStairs(chunk, (NetherFortressGenerator.CorridorStairs) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.CorridorBalcony)
                            FortressHelper.generateCorridorBalcony(chunk, (NetherFortressGenerator.CorridorBalcony) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.CorridorCrossing)
                            FortressHelper.generateCorridorCrossing(chunk, (NetherFortressGenerator.CorridorCrossing) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.CorridorNetherWartsRoom)
                            FortressHelper.generateCorridorNetherWartsRoom(chunk, (NetherFortressGenerator.CorridorNetherWartsRoom) piece, new Random(startPosLong));
                        else if (piece instanceof NetherFortressGenerator.BridgeEnd)
                            FortressHelper.generateBridgeEnd(chunk, (NetherFortressGenerator.BridgeEnd) piece, new Random(startPosLong));
                    }
                }
            }
        }
    }
    
    public static void processStronghold(ProtoChunk chunk, IWorld world)
    {
        for (long startPosLong : chunk.getStructureReferences("Stronghold"))
        {
            ChunkPos startPos = new ChunkPos(startPosLong);
            ProtoChunk startChunk = (ProtoChunk) world.getChunk(startPos.x, startPos.z, ChunkStatus.STRUCTURE_STARTS);
            StructureStart stronghold = startChunk.getStructureStart("Stronghold");
            ChunkPos pos = chunk.getPos();
            if (stronghold != null && stronghold.getBoundingBox().intersectsXZ(pos.getStartX(), pos.getStartZ(), pos.getEndX(), pos.getEndZ()))
            {
                for (StructurePiece piece : stronghold.getChildren())
                {
                    if (piece.getBoundingBox().intersectsXZ(pos.getStartX(), pos.getStartZ(), pos.getEndX(), pos.getEndZ()))
                    {
                        if (piece instanceof StrongholdGenerator.Corridor)
                            StrongholdHelper.generateStrongholdCorridor(chunk, (StrongholdGenerator.Corridor) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.PrisonHall)
                            StrongholdHelper.generateStrongholdPrisonHall(chunk, (StrongholdGenerator.PrisonHall) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.LeftTurn)
                            StrongholdHelper.generateStrongholdLeftTurn(chunk, (StrongholdGenerator.LeftTurn) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.RightTurn)
                            StrongholdHelper.generateStrongholdRightTurn(chunk, (StrongholdGenerator.RightTurn) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.SquareRoom)
                            StrongholdHelper.generateStrongholdSquareRoom(chunk, (StrongholdGenerator.SquareRoom) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.Stairs)
                            StrongholdHelper.generateStrongholdStairs(chunk, (StrongholdGenerator.Stairs) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.SpiralStaircase)
                            StrongholdHelper.generateStrongholdSpiralStaircase(chunk, (StrongholdGenerator.SpiralStaircase) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.FiveWayCrossing)
                            StrongholdHelper.generateStrongholdFiveWayCrossing(chunk, (StrongholdGenerator.FiveWayCrossing) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.ChestCorridor)
                            StrongholdHelper.generateStrongholdChestCorridor(chunk, (StrongholdGenerator.ChestCorridor) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.Library)
                            StrongholdHelper.generateStrongholdLibrary(chunk, (StrongholdGenerator.Library) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.PortalRoom)
                            StrongholdHelper.generateStrongholdPortalRoom(chunk, (StrongholdGenerator.PortalRoom) piece, new Random(startPosLong));
                        else if (piece instanceof StrongholdGenerator.SmallCorridor)
                            StrongholdHelper.generateStrongholdSmallCorridor(chunk, (StrongholdGenerator.SmallCorridor) piece, new Random(startPosLong));
                    }
                }
            }
        }
    }
}
