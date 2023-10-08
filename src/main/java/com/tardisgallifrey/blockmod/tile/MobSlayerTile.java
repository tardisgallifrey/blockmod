package com.tardisgallifrey.blockmod.tile;

import com.tardisgallifrey.blockmod.Init.TileEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class MobSlayerTile extends BlockEntity {
    public MobSlayerTile(BlockPos pos, BlockState state) {

        super(TileEntityInit.MOB_SLAYER.get(), pos, state);
    }

    int timer = 0;
    boolean isActive = true;
    final int RANGE = 5;

    // MobSlayerTile.java
    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        MobSlayerTile tile = (MobSlayerTile) be;

        // MobSlayerTile#tick
        if (!level.isClientSide() && tile.isActive){
            tile.timer++;
            if (tile.timer > 20){
                tile.timer = 0;

                // only do this once per second
                tile.hurtMobs();
            }
        }
    }


    private void hurtMobs() {
        BlockPos topCorner =
                this.worldPosition.offset(RANGE, RANGE, RANGE);
        BlockPos bottomCorner =
                this.worldPosition.offset(-RANGE, -RANGE, -RANGE);
        AABB box = new AABB(topCorner, bottomCorner);

        assert this.level != null;
        List<Entity> entities = this.level.getEntities(null, box);
        for (Entity target : entities){
            if (target instanceof LivingEntity && !(target instanceof Player)){
                target.hurt(DamageSource.MAGIC, 2);
            }
        }
    }

    public void toggle(){
        this.isActive = !this.isActive;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putBoolean("active", this.isActive);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.isActive = nbt.getBoolean("active");
    }


}
