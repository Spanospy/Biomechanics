package flaxbeard.cyberware.api;

import flaxbeard.cyberware.api.playerdata.OrganPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class OrganType {
    public OrganSlot slot;
    public boolean onlyOne;
    public List<OrganAction> tickWith;
    public List<OrganAction> tickWithout;

    public OrganType(OrganSlot slot, boolean onlyOne, List<OrganAction> tickWithout, List<OrganAction> tickWith) {
        this.slot = slot;
        this.onlyOne = onlyOne;
        this.tickWithout = tickWithout;
        this.tickWith = tickWith;
    }

    public void tick(Player player) {
        if (((OrganPlayer) player).getOrgansData().hasOrganType(this)){
            if (tickWith != null)
                for (OrganAction action : tickWith) action.run(player);
        }else {
            if (tickWithout != null)
                for (OrganAction action : tickWithout) action.run(player);
        }
    }
}
