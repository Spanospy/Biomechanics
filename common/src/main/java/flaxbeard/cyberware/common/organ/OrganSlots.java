package flaxbeard.cyberware.common.organ;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganSlots {
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

    public static class OrganSlot {
        public ResourceLocation icon;
        public OrganSlot(ResourceLocation resourceLocation){
            this.icon = new ResourceLocation(
                    resourceLocation.getNamespace(), "textures/gui/organ_slots/" + resourceLocation.getPath() + ".png"
            );
        }
    }
}
