package flaxbeard.cyberware.api.playerdata;

import net.minecraft.world.entity.player.Player;

public interface OrganPlayer {
    PlayerOrgansData getOrgansData();

    static PlayerOrgansData getOrgansData(Player player) {
        return ((OrganPlayer) player).getOrgansData();
    }
}
