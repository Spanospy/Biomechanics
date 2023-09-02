package flaxbeard.cyberware.api.organ;

import flaxbeard.cyberware.api.playerdata.OrganPlayer;
import net.minecraft.world.entity.player.Player;

public interface TickableOrgan {

    void tick(Player player);

    interface TickAction {
        void tick(Player player);
    }
}
