package flaxbeard.cyberware.common.organ.slot;

import flaxbeard.cyberware.api.OrganSlotType;
import flaxbeard.cyberware.api.registry.CWRegistry;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import static flaxbeard.cyberware.Cyberware.MODID;

public class CWOrganSlotType {
    public static final Map<String, OrganSlotType> ORGAN_SLOT_MAP = new HashMap<>();

    public static final OrganSlotType HEART = register("heart");

    public static OrganSlotType register(String id) {
        OrganSlotType organSlotType = new OrganSlotType(new ResourceLocation(MODID, id));
        ORGAN_SLOT_MAP.put(id, organSlotType);
        return organSlotType;
    }
    public static void register() {
        for (Map.Entry<String, OrganSlotType> organSlotType : ORGAN_SLOT_MAP.entrySet()) {
            CWRegistry.ORGAN_SLOTS.register(new ResourceLocation(MODID, organSlotType.getKey()), organSlotType.getValue());
        }
    }
}
