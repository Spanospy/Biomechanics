package flaxbeard.cyberware.common.organ.cybernetic;

public interface IPowerStoring {
    int getPower();
    int getMaxPower();
    void setPower(int power);
    void addPower(int power);
    void subPower(int power);
}
