package flaxbeard.cyberware.api.registry;

import flaxbeard.cyberware.api.OrganSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class OrganSlotRegistry {
    public static final Map<ResourceLocation, Supplier<OrganSlot>> REGISTRY_ORGAN_SLOTS = new HashMap<>();
    public static final Map<ResourceLocation, OrganSlot> ORGAN_SLOTS = new HashMap<>();

    public static void register(ResourceLocation resourceLocation){
        register(resourceLocation, () -> new OrganSlot(resourceLocation));
    }

    public static void register(ResourceLocation resourceLocation, Supplier<OrganSlot> organSlot){
        REGISTRY_ORGAN_SLOTS.put(resourceLocation, organSlot);
    }

    public static void register(){
        for (Map.Entry<ResourceLocation, Supplier<OrganSlot>> entry : REGISTRY_ORGAN_SLOTS.entrySet()) {
            ORGAN_SLOTS.put(entry.getKey(), entry.getValue().get());
        }
    }

    public static OrganSlot get(ResourceLocation resourceLocation){
        return ORGAN_SLOTS.get(resourceLocation);
    }

    public static List<OrganSlot> getSlots(){
        return new ArrayList<>(ORGAN_SLOTS.values());
    }

}
