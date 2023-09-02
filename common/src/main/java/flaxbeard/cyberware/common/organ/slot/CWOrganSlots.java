package flaxbeard.cyberware.common.organ.slot;

import flaxbeard.cyberware.api.OrganSlot;
import flaxbeard.cyberware.api.registry.OrganSlotRegistry;
import net.minecraft.resources.ResourceLocation;

import static flaxbeard.cyberware.Cyberware.MODID;

public class CWOrganSlots {
    public static final OrganSlot HEART = register("heart");

    public static OrganSlot register(String id) {
        return register(id, new OrganSlot(new ResourceLocation(MODID, id)));
    }

    public static OrganSlot register(String id, OrganSlot slot) {
        OrganSlotRegistry.register(new ResourceLocation(MODID, id), slot);
        return slot;
    }

    public static void register() {}
}
