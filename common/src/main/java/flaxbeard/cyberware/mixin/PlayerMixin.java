package flaxbeard.cyberware.mixin;

import flaxbeard.cyberware.common.CWDamageTypes;
import flaxbeard.cyberware.common.organ.Organ;
import flaxbeard.cyberware.common.organ.Organs;
import flaxbeard.cyberware.common.data.PlayerOrgansData;
import flaxbeard.cyberware.mixininterfaces.IPlayer;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements IPlayer {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow public abstract void die(DamageSource damageSource);

    @Shadow public abstract boolean isCreative();

    @Shadow public abstract boolean isSpectator();

    @Shadow public abstract boolean hurt(DamageSource arg, float f);

    private PlayerOrgansData organsData = new PlayerOrgansData();

    @Override
    public PlayerOrgansData getOrgansData() {
        return organsData;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        for (Organ organ : organsData.getOrgans()) {
            organ.tick((Player)(LivingEntity) this);
        }
        if(!this.isCreative() && !this.isSpectator() && this.isAlive()){
            if (!organsData.hasCyberware(Organs.CYBER_HEART) && !organsData.hasCyberware(Organs.HEART)) {
                this.hurt(new DamageSource(new Holder.Direct<>(CWDamageTypes.NO_HEART)), Float.MAX_VALUE);
            }
        }
    }

    @Inject(method = "respawn", at = @At("HEAD"))
    public void respawn(CallbackInfo ci) {;
        organsData.addDefaultOrgans();
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    public void readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        organsData.readAdditionalSaveData(compoundTag);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    public void addAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        organsData.addAdditionalSaveData(compoundTag);
    }
}
