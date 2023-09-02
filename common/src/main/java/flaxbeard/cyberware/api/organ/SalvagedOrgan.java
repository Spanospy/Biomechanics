package flaxbeard.cyberware.api.organ;

import flaxbeard.cyberware.api.OrganType;

public class SalvagedOrgan extends ManufacturedOrgan {
    public SalvagedOrgan(OrganType type, int max, Organ[] requiredOrgans, Organ[] incompatibleOrgans, float toleranceCost, float powerCost, TickAction tickAction) {
        super(type, max, requiredOrgans, incompatibleOrgans, toleranceCost, powerCost, tickAction);
    }
}