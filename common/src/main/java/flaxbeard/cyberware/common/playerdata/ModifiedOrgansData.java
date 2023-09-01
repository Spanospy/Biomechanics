package flaxbeard.cyberware.common.playerdata;

import flaxbeard.cyberware.common.organ.Organs.Organ;
import flaxbeard.cyberware.common.organ.Organs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ModifiedOrgansData {
    Map<Organ, Integer> toAdd = new HashMap<>();
    Map<Organ, Integer> toRemove = new HashMap<>();

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        CompoundTag modifiedOrgans = new CompoundTag();
        ListTag listTagToAdd = new ListTag();
        for (Organ organ : toAdd.keySet()) {
            CompoundTag nbt = new CompoundTag();
            nbt.putString("name", Organs.getRegistryName(organ).toString());
            nbt.putInt("count", toAdd.get(organ));
            listTagToAdd.add(nbt);
        }
        ListTag listTagToRemove = new ListTag();
        for (Organ organ : toRemove.keySet()) {
            CompoundTag nbt = new CompoundTag();
            nbt.putString("name", Organs.getRegistryName(organ).toString());
            nbt.putInt("count", toRemove.get(organ));
            listTagToRemove.add(nbt);
        }
        modifiedOrgans.put("toAdd", listTagToAdd);
        modifiedOrgans.put("toRemove", listTagToRemove);
        compoundTag.put("modifiedOrgans", modifiedOrgans);
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        CompoundTag modifiedOrgans = compoundTag.getCompound("modifiedOrgans");
        ListTag listTagToAdd = modifiedOrgans.getList("toAdd", 10);
        for (int i = 0; i < listTagToAdd.size(); i++) {
            CompoundTag nbt = listTagToAdd.getCompound(i);
            Organ organ = Organs.get(new ResourceLocation(nbt.getString("name")));
            int count = nbt.getInt("count");
            toAdd.put(organ, count);
        }
        ListTag listTagToRemove = modifiedOrgans.getList("toRemove", 10);
        for (int i = 0; i < listTagToRemove.size(); i++) {
            CompoundTag nbt = listTagToRemove.getCompound(i);
            Organ organ = Organs.get(new ResourceLocation(nbt.getString("name")));
            int count = nbt.getInt("count");
            toRemove.put(organ, count);
        }
    }

    public void addOrgan(Organ organ) {
        if (toRemove.containsKey(organ)) {
            int count = toRemove.get(organ);
            if (count > 1) {
                toRemove.put(organ, count - 1);
            } else {
                toRemove.remove(organ);
            }
        } else {
            toAdd.put(organ, toAdd.getOrDefault(organ, 0) + 1);
        }
    }

    public void removeOrgan(Organ organ) {
        if (toAdd.containsKey(organ)) {
            int count = toAdd.get(organ);
            if (count > 1) {
                toAdd.put(organ, count - 1);
            } else {
                toAdd.remove(organ);
            }
        } else {
            toRemove.put(organ, toRemove.getOrDefault(organ, 0) + 1);
        }
    }
}
