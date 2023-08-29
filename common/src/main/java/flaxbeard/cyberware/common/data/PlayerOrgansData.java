package flaxbeard.cyberware.common.data;

import flaxbeard.cyberware.common.organ.Organ;
import flaxbeard.cyberware.common.organ.Organs;
import flaxbeard.cyberware.common.organ.cybernetic.interfaces.ICybernetic;
import flaxbeard.cyberware.common.organ.cybernetic.interfaces.IPowerStoring;
import flaxbeard.cyberware.common.organ.cybernetic.interfaces.ISalvaged;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerOrgansData {
    private Map<Organ, Integer> cyberwares = new HashMap<>();
    private float storedPower = 0;

    public Map<Organ, Integer> getCyberwares() {
        return cyberwares;
    }

    public List<Organ> getOrgans() {
        return new ArrayList<>(cyberwares.keySet());
    }

    public void addCyberware(Organ organ) {
        if (cyberwares.containsKey(organ)) {
            cyberwares.put(organ, cyberwares.get(organ) + 1);
        } else {
            cyberwares.put(organ, 1);
        }
    }

    public void removeCyberware(Organ organ) {
        if (cyberwares.containsKey(organ)) {
            if (cyberwares.get(organ) > 1) {
                cyberwares.put(organ, cyberwares.get(organ) - 1);
            } else {
                cyberwares.remove(organ);
            }
        }
    }

    public boolean hasCyberware(Organ organ) {
        return cyberwares.containsKey(organ);
    }

    public float getMaxPower() {
        int maxPower = 0;
        for (Organ organ : cyberwares.keySet()) {
            if (organ instanceof IPowerStoring)
                maxPower += ((IPowerStoring) organ).getMaxPower();
        }
        return maxPower;
    }

    public float getStoredPower() {
        return storedPower;
    }

    public void addStoredPower(float power) {
        if (storedPower + power > getMaxPower()) {
            storedPower = getMaxPower();
        } else {
            storedPower += power;
        }
    }

    public void subStoredPower(float power) {
        if (storedPower - power < 0) {
            storedPower = 0;
        } else {
            storedPower -= power;
        }
    }

    public void addDefaultOrgans() {
        cyberwares.clear();
        addCyberware(Organs.HEART);
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        if (cyberwares.isEmpty()) {
            addDefaultOrgans();
        }
        CompoundTag tag = compoundTag.getCompound("cyberware");
        ListTag listTag = tag.getList("organs", 10);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag nbt = listTag.getCompound(i);
            Organ organ = Organs.getOrgan(nbt.getString("name"));
            if (organ != null) {
                cyberwares.put(organ, nbt.getInt("count"));
            }
        }
        storedPower = tag.getFloat("storedPower");
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();
        for (Organ organ : cyberwares.keySet()) {
            CompoundTag nbt = new CompoundTag();
            nbt.putString("name", organ.name);
            nbt.putInt("count", cyberwares.get(organ));
            listTag.add(nbt);
        }
        tag.put("organs", listTag);
        tag.putFloat("storedPower", storedPower);
        compoundTag.put("cyberware", tag);
    }

    public int getTolerance() {
        int tolerance = 100;
        for (Organ organ : cyberwares.keySet()) {
            int toleranceCost = 0;
            if (organ instanceof ICybernetic) {
                toleranceCost += ((ICybernetic) organ).getToleranceCost() * cyberwares.get(organ);
            }
            if (organ instanceof ISalvaged) {
                toleranceCost *= ((ISalvaged) organ).getToleranceCostMultiplicator();
            }
            tolerance -= toleranceCost;
        }
        return tolerance;
    }
}