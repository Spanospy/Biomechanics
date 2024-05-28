package flaxbeard.cyberware.common.effect;

import flaxbeard.cyberware.common.CWDamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class CyberRejectionEffect extends MobEffect {
    protected CyberRejectionEffect() {
        super(MobEffectCategory.HARMFUL, 0x651C32);
    }

    @Override
    public boolean isInstantenous(){
        return false;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier){
        return duration % new Random().nextInt(20, 100) == 0;
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int i) {
        livingEntity.hurt(CWDamageTypes.of(livingEntity.level(), CWDamageTypes.CYBER_REJECTION), 1.0F);
        return true;
    }
}
