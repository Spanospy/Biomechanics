package flaxbeard.cyberware.common.organ.cybernetic.interfaces;

public interface ISalvaged extends ICybernetic{
    float toleranceCostMultiplicator = 1.75f;
    default float getToleranceCostMultiplicator(){
        return toleranceCostMultiplicator;
    };
}
