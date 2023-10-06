package flaxbeard.cyberware.api;

import flaxbeard.cyberware.api.playerdata.OrganPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class OrganType {
    public OrganSlotType slot;
    public boolean onlyOne;
    public List<PlayerOrganTick> tickWith;
    public List<PlayerOrganTick> tickWithout;

    public OrganType(OrganSlotType slot, boolean onlyOne, List<PlayerOrganTick> tickWithout, List<PlayerOrganTick> tickWith) {
        this.slot = slot;
        this.onlyOne = onlyOne;
        this.tickWithout = tickWithout;
        this.tickWith = tickWith;
    }

    public void tick(Player player) {
        if (((OrganPlayer) player).getOrgansData().hasOrganType(this)){
            if (tickWith != null)
                for (PlayerOrganTick action : tickWith) action.tick(player);
        }else {
            if (tickWithout != null)
                for (PlayerOrganTick action : tickWithout) action.tick(player);
        }
    }
}
