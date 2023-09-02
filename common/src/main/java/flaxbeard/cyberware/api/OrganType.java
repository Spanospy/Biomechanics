package flaxbeard.cyberware.api;

import flaxbeard.cyberware.api.playerdata.OrganPlayer;
import net.minecraft.world.entity.player.Player;

public class OrganType {
    public OrganSlot slot;
    public Tick tickNotPresent;
    public Tick tickPresent;

    public OrganType(OrganSlot slot, Tick tickNotPresent, Tick tickPresent) {
        this.slot = slot;
        this.tickNotPresent = tickNotPresent;
        this.tickPresent = tickPresent;
    }

    public void tick(Player player) {
        if (((OrganPlayer) player).getOrgansData().hasOrganType(this)){
            tickNotPresent.tick(player);
        }else {
            tickPresent.tick(player);
        }
    }

    public interface Tick {
        void tick(Player player);
    }
}
