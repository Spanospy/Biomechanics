package flaxbeard.cyberware.common.organ.cybernetic;

import flaxbeard.cyberware.mixininterfaces.IPlayer;
import net.minecraft.world.entity.player.Player;

public interface IPowerConsuming {
    float getPowerConsumption();
    default void tickPower(Player player){
        ((IPlayer) player).getOrgansData().subStoredPower(getPowerConsumption());
    }
}
