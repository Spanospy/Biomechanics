package flaxbeard.cyberware.api.organ;

import flaxbeard.cyberware.api.OrganType;
import flaxbeard.cyberware.api.playerdata.OrganPlayer;
import net.minecraft.world.entity.player.Player;

public class ManufacturedOrgan extends Organ implements TickableOrgan, ToleranceCostingOrgan {
    private float toleranceCost;
    private TickAction tickAction;
    private float powerCost;

    public ManufacturedOrgan(OrganType type, int max, Organ[] requiredOrgans, Organ[] incompatibleOrgans, float toleranceCost, float powerCost, TickAction tickAction) {
        super(type, max, requiredOrgans, incompatibleOrgans);
        this.toleranceCost = toleranceCost;
        this.tickAction = tickAction;
        this.powerCost = powerCost;
    }

    @Override
    public float getToleranceCost() {
        return toleranceCost;
    }

    public float getPowerCost() {
        return powerCost;
    }

    @Override
    public void tick(Player player) {
        tickAction.tick(player);
        OrganPlayer.getOrgansData(player).subStoredPower(powerCost);
    }
}
