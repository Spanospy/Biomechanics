package flaxbeard.cyberware.api.registry;

import flaxbeard.cyberware.api.Organ;
import flaxbeard.cyberware.api.OrganOrigin;
import flaxbeard.cyberware.api.OrganSlot;
import flaxbeard.cyberware.api.OrganType;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CWRegistry<T> {
    public final Map<ResourceLocation, T> registry = new HashMap<>();
    public T register(ResourceLocation resourceLocation, T value){
        registry.put(resourceLocation, value);
        return value;
    }
    public T get(ResourceLocation resourceLocation){
        return registry.get(resourceLocation);
    }
    public Map<ResourceLocation, T> getRegistry(){
        return registry;
    }
    public ResourceLocation getKey(T value){
        for (Map.Entry<ResourceLocation, T> entry : registry.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static final CWRegistry<OrganOrigin> ORGAN_ORIGINS = new CWRegistry<>();
    public static final CWRegistry<OrganSlot> ORGAN_SLOTS = new CWRegistry<>();
    public static final CWRegistry<OrganType> ORGAN_TYPES = new CWRegistry<>();
    public static final CWRegistry<Organ> ORGANS = new CWRegistry<>();
}
