package flaxbeard.cyberware.api.playerdata;

import flaxbeard.cyberware.api.Organ;
import flaxbeard.cyberware.api.OrganSlotType;

public class OrganSlot {
    public int count;
    public Organ organ;
    public OrganSlotType type;

    public OrganSlot(Organ organ, OrganSlotType type, int count){
        this.organ = organ;
        this.type = type;
        this.count = count;
    }

    public boolean isCompatible(Organ organ){
        return this.organ.getType().equals(organ.getType());
    }

    public int getCount(){
        return count;
    }

    public Organ getOrgan(){
        return organ;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void setOrgan(Organ organ){
        this.organ = organ;
    }

    public OrganSlotType getType(){
        return type;
    }

    public void setType(OrganSlotType type){
        this.type = type;
    }
}
