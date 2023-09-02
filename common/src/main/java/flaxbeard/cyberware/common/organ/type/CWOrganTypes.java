package flaxbeard.cyberware.common.organ.type;

import flaxbeard.cyberware.api.OrganType;
import flaxbeard.cyberware.api.registry.OrganTypeRegistry;
import flaxbeard.cyberware.common.CWDamageTypes;
import flaxbeard.cyberware.common.organ.slot.CWOrganSlots;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;

import static flaxbeard.cyberware.Cyberware.MODID;

public class CWOrganTypes {
    public static final OrganType HEART = register(
            "heart",
            new OrganType(
                    CWOrganSlots.HEART,
                    true,
                    (player) -> {
                        player.hurt(new DamageSource(new Holder.Direct<>(CWDamageTypes.NO_HEART)), Integer.MAX_VALUE);
                    },
                    null
            )
    );

    public static OrganType register(String id, OrganType organType) {
        OrganTypeRegistry.register(new ResourceLocation(MODID, id), organType);
        return organType;
    }
    public static void register() {}
}
