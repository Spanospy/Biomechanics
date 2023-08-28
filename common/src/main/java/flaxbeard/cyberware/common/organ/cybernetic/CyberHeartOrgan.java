package flaxbeard.cyberware.common.organ.cybernetic;

import flaxbeard.cyberware.common.CWDamageTypes;
import flaxbeard.cyberware.common.organ.Organ;
import flaxbeard.cyberware.common.organ.OrganSlots;
import flaxbeard.cyberware.common.organ.Organs;
import flaxbeard.cyberware.common.organ.biological.HeartOrgan;
import flaxbeard.cyberware.interfaces.CyberwareAbilities;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class CyberHeartOrgan extends HeartOrgan implements IPowerConsuming, ICybernetic {
    public CyberHeartOrgan() {
        super(OrganSlots.HEART, new Organ[]{Organs.HEART}, "cyber_heart");
    }

    @Override
    public void tick(Player player) {
        if (((CyberwareAbilities)player).getStoredPower() <= 0) {
            player.die(new DamageSource(new Holder.Direct<>(CWDamageTypes.NO_POWER_HEART.get())));
        } else {
            this.tickPower(player);
            if (player.hasEffect(MobEffects.WEAKNESS)) {
                player.removeEffect(MobEffects.WEAKNESS);
            }
        }
    }

    @Override
    public float getPowerConsumption() {
        return 1/20f;
    }
}
