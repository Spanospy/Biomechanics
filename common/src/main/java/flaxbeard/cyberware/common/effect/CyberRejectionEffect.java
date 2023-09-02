package flaxbeard.cyberware.common.effect;

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
        super.applyEffectTick(livingEntity, i);
    }

    @Override
    public boolean isDurationEffectTick(int i, int j) {
        int k = 20 >> j;
        if (k > 0) {
            return i % k == 0;
        }
        return false;
    }
}