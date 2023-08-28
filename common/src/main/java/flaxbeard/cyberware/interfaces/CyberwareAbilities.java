package flaxbeard.cyberware.interfaces;

import flaxbeard.cyberware.common.organ.Organ;
import net.minecraft.nbt.Tag;

import java.util.List;

public interface CyberwareAbilities {
    List<Organ> getCyberwares();
    void addCyberware(Organ organ);
    void removeCyberware(Organ organ);
    boolean hasCyberware(Organ organ);
    float getMaxPower();
    float getStoredPower();
    void setStoredPower(float power);
    void addStoredPower(float power);
    void subStoredPower(float power);
}
