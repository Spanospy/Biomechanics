package flaxbeard.cyberware.common.organ.cybernetic;

import flaxbeard.cyberware.common.organ.cybernetic.interfaces.ISalvaged;

public class SalvagedCyberHeartOrgan extends CyberHeartOrgan implements ISalvaged {
    public SalvagedCyberHeartOrgan(){
        super("salvaged_cyber_heart");
    }
    @Override
    public float getToleranceCost() {
        return super.getToleranceCost() * getToleranceCostMultiplicator();
    }
}
