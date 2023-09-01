package flaxbeard.cyberware.common.organ;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class OrganTypes {
    public static final Map<ResourceLocation, OrganType> ORGAN_TYPES = new HashMap<>();

    public static void register(ResourceLocation resourceLocation, OrganSlots.OrganSlot slot){
        register(resourceLocation, new OrganType(slot));
    }

    public static void register(ResourceLocation resourceLocation, OrganType organType){
        ORGAN_TYPES.put(resourceLocation, organType);
    }

    public static OrganType get(ResourceLocation resourceLocation){
        return ORGAN_TYPES.get(resourceLocation);
    }

    public static class OrganType {
        public OrganSlots.OrganSlot slot;

        public OrganType(OrganSlots.OrganSlot slot) {
            this.slot = slot;
        }
    }
}
