package flaxbeard.cyberware.api.organ;

import flaxbeard.cyberware.api.OrganType;

public class BiologicalOrgan extends Organ {
    public BiologicalOrgan(OrganType type, int max, Organ[] requiredOrgans, Organ[] incompatibleOrgans) {
        super(type, max, requiredOrgans, incompatibleOrgans);
    }
}
