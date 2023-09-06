package flaxbeard.cyberware.api.registry;

import flaxbeard.cyberware.api.OrganType;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class OrganTypeRegistry {
    public static final Map<ResourceLocation, OrganType> ORGAN_TYPES = new HashMap<>();

    public static void register(ResourceLocation resourceLocation, OrganType organType){
        ORGAN_TYPES.put(resourceLocation, organType);
    }

    public static OrganType get(ResourceLocation resourceLocation){
        return ORGAN_TYPES.get(resourceLocation);
    }
}
