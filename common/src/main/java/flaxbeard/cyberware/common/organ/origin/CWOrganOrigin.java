package flaxbeard.cyberware.common.organ.origin;

import flaxbeard.cyberware.api.OrganOrigin;
import flaxbeard.cyberware.api.OrganSlotType;
import flaxbeard.cyberware.api.registry.CWRegistry;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import static flaxbeard.cyberware.Cyberware.MODID;

public class CWOrganOrigin {
    public static final Map<String, OrganOrigin> ORGAN_ORIGIN_MAP = new HashMap<>();

    public static final OrganOrigin ORGANIC = register("organic");

    public static OrganOrigin register(String id) {
        OrganOrigin organOrigin = new OrganOrigin(new ResourceLocation(MODID, id));
        ORGAN_ORIGIN_MAP.put(id, organOrigin);
        return organOrigin;
    }
    public static void register() {
        for (Map.Entry<String, OrganOrigin> organSlotType : ORGAN_ORIGIN_MAP.entrySet()) {
            CWRegistry.ORGAN_ORIGINS.register(new ResourceLocation(MODID, organSlotType.getKey()), organSlotType.getValue());
        }
    }
}
