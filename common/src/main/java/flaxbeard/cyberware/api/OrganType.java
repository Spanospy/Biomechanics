package flaxbeard.cyberware.api;

import flaxbeard.cyberware.api.playerdata.OrganPlayer;
import net.minecraft.world.entity.player.Player;

public class OrganType {
    public OrganSlot slot;
    public boolean onlyOne;
    public Tick tickNotPresent;
    public Tick tickPresent;

    public OrganType(OrganSlot slot, boolean onlyOne, Tick tickNotPresent, Tick tickPresent) {
        this.slot = slot;
        this.onlyOne = onlyOne;
        this.tickNotPresent = tickNotPresent;
        this.tickPresent = tickPresent;
    }

    public void tick(Player player) {
        if (((OrganPlayer) player).getOrgansData().hasOrganType(this)){
            if (tickPresent != null) tickPresent.tick(player);
        }else {
            if (tickNotPresent != null) tickNotPresent.tick(player);
        }
    }

    public interface Tick {
        void tick(Player player);
    }
}
