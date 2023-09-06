package flaxbeard.cyberware.api.registry;

import flaxbeard.cyberware.api.OrganOrigin;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class OrganOriginRegistry {
    public static final Map<ResourceLocation, OrganOrigin> ORGAN_ORGINS = new HashMap<>();

    public static void register(ResourceLocation resourceLocation){
        register(resourceLocation, new OrganOrigin(resourceLocation.getPath()));
    }
    public static void register(ResourceLocation resourceLocation, OrganOrigin organSlot){
        ORGAN_ORGINS.put(resourceLocation, organSlot);
    }

    public static OrganOrigin get(ResourceLocation resourceLocation){
        return ORGAN_ORGINS.get(resourceLocation);
    }
}
