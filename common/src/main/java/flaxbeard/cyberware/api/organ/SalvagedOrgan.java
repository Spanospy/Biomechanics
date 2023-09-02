package flaxbeard.cyberware.api.organ;

import flaxbeard.cyberware.api.OrganType;

public class SalvagedOrgan extends ManufacturedOrgan {
    public SalvagedOrgan(OrganType type, int max, Organ[] requiredOrgans, Organ[] incompatibleOrgans, float toleranceCost, float powerCost, TickAction tickAction) {
        super(type, max, requiredOrgans, incompatibleOrgans, toleranceCost, powerCost, tickAction);
    }

    public static SalvagedOrgan from(ManufacturedOrgan organ){
        return new SalvagedOrgan(
                organ.getType(),
                organ.getMax(),
                organ.getRequired().toArray(new Organ[0]),
                organ.getIncompatible().toArray(new Organ[0]),
                organ.getToleranceCost() * 1.5f,
                organ.getPowerCost(),
                organ::tick
        );
    }
}