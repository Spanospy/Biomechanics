package flaxbeard.cyberware.common.organ.cybernetic;

import flaxbeard.cyberware.interfaces.CyberwareAbilities;
import net.minecraft.world.entity.player.Player;

public interface IPowerConsuming {
    float getPowerConsumption();
    default void tickPower(Player player){
        ((CyberwareAbilities) player).subStoredPower(this.getPowerConsumption());
    }
}
