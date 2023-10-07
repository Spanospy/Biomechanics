package flaxbeard.cyberware.mixin;

import flaxbeard.cyberware.api.OrganType;
import flaxbeard.cyberware.api.Organ;
import flaxbeard.cyberware.api.playerdata.PlayerOrgansData;
import flaxbeard.cyberware.api.registry.CWRegistry;
import flaxbeard.cyberware.api.playerdata.OrganPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements OrganPlayer {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow public abstract boolean hurt(DamageSource arg, float f);

    private PlayerOrgansData organsData = new PlayerOrgansData();

    @Override
    public PlayerOrgansData getOrgansData() {
        return organsData;
    }
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo info) {
        organsData.addDefaultOrgans();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        for (Organ organ : organsData.getOrgans()) {
            organ.tick((Player)(Object) this);
        }
        for (OrganType organType : CWRegistry.ORGAN_TYPES.getRegistry().values()) {
            organType.tick((Player)(Object) this);
        }

        /*
        if (organsData.getTolerance() <= 0) {
            this.hurt(new DamageSource(new Holder.Direct<>(CWDamageTypes.CYBER_REJECTION)), Float.MAX_VALUE);
        } else if (organsData.getTolerance() <= PlayerOrgansData.TOLERANCE*(1/4f)) {
            this.addEffect(new MobEffectInstance(CWEffects.CYBER_REJECTION.get(), 1, 0, false, false));
        }
         */
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
