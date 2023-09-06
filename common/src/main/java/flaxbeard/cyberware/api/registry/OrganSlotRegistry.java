package flaxbeard.cyberware.api.registry;

import flaxbeard.cyberware.api.OrganSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class OrganSlotRegistry {
    public static final Map<ResourceLocation, OrganSlot> ORGAN_SLOTS = new HashMap<>();

    public static void register(ResourceLocation resourceLocation){
        register(resourceLocation, new OrganSlot(resourceLocation));
    }

    public static void register(ResourceLocation resourceLocation, OrganSlot organSlot){
        ORGAN_SLOTS.put(resourceLocation, organSlot);
    }

    public static OrganSlot get(ResourceLocation resourceLocation){
        return ORGAN_SLOTS.get(resourceLocation);
    }

    public static List<OrganSlot> getSlots(){
        return new ArrayList<>(ORGAN_SLOTS.values());
    }

}
