package flaxbeard.cyberware.api;

import flaxbeard.cyberware.api.playerdata.OrganPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class OrganType {
    public OrganSlotType slot;
    public int maximum;
    public PlayerOrganTick tickWith;
    public PlayerOrganTick tickWithout;

    public OrganType(OrganSlotType slot, int maximum, PlayerOrganTick tickWithout, PlayerOrganTick tickWith) {
        this.slot = slot;
        this.maximum = maximum;
        this.tickWithout = tickWithout;
        this.tickWith = tickWith;
    }

    public void tick(Player player) {
        if (((OrganPlayer) player).getOrgansData().hasOrganType(this)){
            if (tickWith != null)
                tickWith.tick(player);
        }else {
            if (tickWithout != null)
                tickWithout.tick(player);
        }
    }
}
