package flaxbeard.cyberware.common.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;

public class NoLungsEffect extends MobEffect {
    protected NoLungsEffect() {
        super(MobEffectCategory.HARMFUL, 0xFFFD37);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {
        if (livingEntity.isEyeInFluid(FluidTags.WATER) && !livingEntity.level().getBlockState(BlockPos.containing(livingEntity.getX(), livingEntity.getEyeY(), livingEntity.getZ())).is(Blocks.BUBBLE_COLUMN))
            if (!livingEntity.canBreatheUnderwater() && !MobEffectUtil.hasWaterBreathing(livingEntity) && (!(livingEntity instanceof Player) || !((Player)livingEntity).getAbilities().invulnerable))
                return;

        livingEntity.setAirSupply(livingEntity.decreaseAirSupply(livingEntity.getAirSupply()));
        super.applyEffectTick(livingEntity, i);
    }

    @Override
    public boolean isDurationEffectTick(int i, int j) {
        int k = 30 >> j;
        if (k > 0) {
            return i % k == 0;
        }
        return false;
    }
}
