package flaxbeard.cyberware.common.organ.type;

import flaxbeard.cyberware.api.OrganSlot;
import flaxbeard.cyberware.api.OrganType;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.level.block.Blocks;

public class LungOrganType extends OrganType {
    private static int tickNotPresent = 0;
    public LungOrganType(OrganSlot slot, boolean onlyOne, Tick tickPresent) {
        super(slot, onlyOne, player -> {
            if (tickNotPresent % 20 != 0) {
                tickNotPresent++;
                return;
            }

            if (!player.isEyeInFluid(FluidTags.WATER) && player.level().getBlockState(BlockPos.containing(player.getX(), player.getEyeY(), player.getZ())).is(Blocks.BUBBLE_COLUMN))
                if (player.canBreatheUnderwater() || MobEffectUtil.hasWaterBreathing(player))
                    player.setAirSupply(player.decreaseAirSupply(player.getAirSupply()));

            tickNotPresent = 0;
        }, tickPresent);
    }
}
