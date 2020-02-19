package pt.skyblock.util;

import net.minecraft.entity.boss.dragon.EnderDragonSpawnState;
import net.minecraft.entity.decoration.EnderCrystalEntity;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public interface IEnderDragonFight
{
    EnderDragonSpawnState getDragonSpawnState();
    
    List<EnderCrystalEntity> getCrystals();
    
    BlockPos getExitPortalLocation();
}
