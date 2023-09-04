package flaxbeard.cyberware.api;

import flaxbeard.cyberware.api.playerdata.OrganPlayer;
import flaxbeard.cyberware.utils.CommandUtils;
import net.minecraft.world.entity.player.Player;

public class OrganType {
    public OrganSlot slot;
    public boolean onlyOne;
    public String commandTickPresent;
    public String commandTickNotPresent;

    public OrganType(OrganSlot slot, boolean onlyOne, String commandTickNotPresent, String commandTickPresent) {
        this.slot = slot;
        this.onlyOne = onlyOne;
        this.commandTickNotPresent = commandTickNotPresent;
        this.commandTickPresent = commandTickPresent;
    }

    public OrganType(OrganSlot slot, boolean onlyOne) {
        this(slot, onlyOne, null, null);
    }

    public void tick(Player player) {
        if (((OrganPlayer) player).getOrgansData().hasOrganType(this)){
            if (commandTickPresent != null) CommandUtils.run(commandTickPresent, player);
        }else {
            if (commandTickNotPresent != null) CommandUtils.run(commandTickNotPresent, player);
        }
    }
}
