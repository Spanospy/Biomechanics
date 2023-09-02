package flaxbeard.cyberware.api.registry;

import flaxbeard.cyberware.api.organ.Organ;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class OrganRegistry {
    public static Map<ResourceLocation, Organ> ORGANS = new HashMap<>();

    public static Organ get(ResourceLocation resourceLocation){
        return ORGANS.get(resourceLocation);
    }

    public static ResourceLocation getRegistryName(Organ organ){
        for (Map.Entry<ResourceLocation, Organ> entry : ORGANS.entrySet()) {
            if (entry.getValue().equals(organ)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void register(ResourceLocation resourceLocation, Organ organ){
        ORGANS.put(resourceLocation, organ);
    }
}
