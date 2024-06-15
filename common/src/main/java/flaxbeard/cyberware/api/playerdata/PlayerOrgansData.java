package flaxbeard.cyberware.api.playerdata;

import flaxbeard.cyberware.api.OrganType;
import flaxbeard.cyberware.api.Organ;
import flaxbeard.cyberware.api.registry.CWRegistry;
import flaxbeard.cyberware.common.organ.CWOrgan;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerOrgansData {
    public static List<Organ> DEFAULTS = List.of(
            CWOrgan.HEART
    );
    public static float TOLERANCE = 100;
    private final Map<Organ, Integer> CURRENTS = new HashMap<>();
    private float storedPower = 0;

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

    public boolean hasOrgan(Organ organ) {
        return CURRENTS.containsKey(organ);
    }

    public boolean hasOrganType(OrganType type){
        for (Organ organ : CURRENTS.keySet()) {
            if (organ.getType().equals(type)) {
                return true;
            }
        }
        return false;
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
        CompoundTag tag = compoundTag.getCompound("cyberware");
        ListTag listTag = tag.getList("organs", 10);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag nbt = listTag.getCompound(i);
            Organ organ = CWRegistry.ORGANS.get(new ResourceLocation(nbt.getString("type")));
            if (organ != null) {
                CURRENTS.put(organ, nbt.getInt("count"));
            }
        }
        storedPower = tag.getFloat("storedPower");
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();
        for (Organ organ : CURRENTS.keySet()) {
            CompoundTag nbt = new CompoundTag();
            nbt.putString("type", CWRegistry.ORGANS.getKey(organ).toString());
            nbt.putInt("count", CURRENTS.get(organ));
            listTag.add(nbt);
        }
        tag.put("organs", listTag);
        tag.putFloat("storedPower", storedPower);
        compoundTag.put("cyberware", tag);
    }

    public CompoundTag writeToNbt() {
        CompoundTag compoundTag = new CompoundTag();
        addAdditionalSaveData(compoundTag);
        return compoundTag;
    }

    public float getTolerance() {
        float tolerance = TOLERANCE;
        for (Organ organ : CURRENTS.keySet()) {
            float toleranceCost = 0;

            toleranceCost += organ.getToleranceCost() * CURRENTS.get(organ);

            tolerance -= toleranceCost;
        }
        return tolerance;
    }
}