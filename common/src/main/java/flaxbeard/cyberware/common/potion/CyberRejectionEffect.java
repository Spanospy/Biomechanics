package flaxbeard.cyberware.common.potion;

import flaxbeard.cyberware.common.CWDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CyberRejectionEffect extends MobEffect {
    protected CyberRejectionEffect() {
        super(MobEffectCategory.HARMFUL, 0x990000);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {
        livingEntity.hurt(new DamageSource(new Holder.Direct<>(CWDamageTypes.CYBER_REJECTION)), 2.5F);
    }
}