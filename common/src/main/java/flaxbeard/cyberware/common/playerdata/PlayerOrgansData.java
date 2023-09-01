package flaxbeard.cyberware.common.playerdata;

import flaxbeard.cyberware.common.organ.Organs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static flaxbeard.cyberware.common.organ.Organs.Organ;

public class PlayerOrgansData {
    public static List<Organ> DEFAULTS = new ArrayList<>();
    private Map<Organ, Integer> CURRENTS = new HashMap<>();
    private float storedPower = 0;
    public ModifiedOrgansData modifiedOrgansData = new ModifiedOrgansData();
    public Map<Organ, Integer> getCURRENTS() {
        return CURRENTS;
    }

    public List<Organ> getOrgans() {
        return new ArrayList<>(CURRENTS.keySet());
    }

    public void addCyberware(Organ organ) {
        if (CURRENTS.containsKey(organ)) {
            CURRENTS.put(organ, CURRENTS.get(organ) + 1);
        } else {
            CURRENTS.put(organ, 1);
        }
    }

    public void removeCyberware(Organ organ) {
        if (CURRENTS.containsKey(organ)) {
            if (CURRENTS.get(organ) > 1) {
                CURRENTS.put(organ, CURRENTS.get(organ) - 1);
            } else {
                CURRENTS.remove(organ);
            }
        }
    }

    public boolean hasCyberware(Organ organ) {
        return CURRENTS.containsKey(organ);
    }

    public float getMaxPower() {
        int maxPower = 0;
        /*
        for (Organ organ : CURRENTS.keySet()) {
            if (organ instanceof IPowerStoring)
                maxPower += ((IPowerStoring) organ).getMaxPower();
        }
         */
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
        CURRENTS.clear();
        for (Organ organ : DEFAULTS)
            addCyberware(organ);
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        if (CURRENTS.isEmpty()) {
            addDefaultOrgans();
        }
        CompoundTag tag = compoundTag.getCompound("cyberware");
        ListTag listTag = tag.getList("organs", 10);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag nbt = listTag.getCompound(i);
            Organ organ = Organs.get(new ResourceLocation(nbt.getString("type")));
            if (organ != null) {
                CURRENTS.put(organ, nbt.getInt("count"));
            }
        }
        modifiedOrgansData.readAdditionalSaveData(tag);
        storedPower = tag.getFloat("storedPower");
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();
        for (Organs.Organ organ : CURRENTS.keySet()) {
            CompoundTag nbt = new CompoundTag();
            nbt.putString("type", Organs.getRegistryName(organ).toString());
            nbt.putInt("count", CURRENTS.get(organ));
            listTag.add(nbt);
        }
        tag.put("organs", listTag);
        tag.putFloat("storedPower", storedPower);
        modifiedOrgansData.addAdditionalSaveData(tag);
        compoundTag.put("cyberware", tag);
    }

    public CompoundTag writeToNbt() {
        CompoundTag compoundTag = new CompoundTag();
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();
        for (Organ organ : CURRENTS.keySet()) {
            CompoundTag nbt = new CompoundTag();
            nbt.putString("type", Organs.getRegistryName(organ).toString());
            nbt.putInt("count", CURRENTS.get(organ));
            listTag.add(nbt);
        }
        tag.put("organs", listTag);
        tag.putFloat("storedPower", storedPower);
        modifiedOrgansData.addAdditionalSaveData(tag);
        compoundTag.put("cyberware", tag);
        return compoundTag;
    }

    public float getTolerance() {
        float tolerance = 100;
        for (Organ organ : CURRENTS.keySet()) {
            float toleranceCost = 0;
            /*
            if (organ instanceof ICybernetic) {
                toleranceCost += ((ICybernetic) organ).getToleranceCost() * CURRENTS.get(organ);
            }
            if (organ instanceof ISalvaged) {
                toleranceCost *= ((ISalvaged) organ).getToleranceCostMultiplicator();
            }
             */
            tolerance -= toleranceCost;
        }
        return tolerance;
    }
}