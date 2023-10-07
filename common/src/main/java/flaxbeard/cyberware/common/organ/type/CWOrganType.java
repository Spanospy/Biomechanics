package flaxbeard.cyberware.common.organ.type;

import flaxbeard.cyberware.Cyberware;
import flaxbeard.cyberware.api.OrganType;
import flaxbeard.cyberware.api.registry.CWRegistry;
import flaxbeard.cyberware.common.CWDamageTypes;
import flaxbeard.cyberware.common.organ.slot.CWOrganSlotType;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;

import java.util.HashMap;
import java.util.Map;

import static flaxbeard.cyberware.Cyberware.MODID;

public class CWOrganType {
    public static final Map<String, OrganType> ORGAN_TYPE_MAP = new HashMap<>();

    public static final OrganType HEART = register("heart", new OrganType(
            CWOrganSlotType.HEART,
            1,
            (player) -> {
                if (player.isAlive())
                    player.hurt(new DamageSource(Holder.direct(CWDamageTypes.NO_HEART)), Float.MAX_VALUE);
            },
            null
    ));

    public static OrganType register(String id, OrganType organType) {
        ORGAN_TYPE_MAP.put(id, organType);
        return organType;
    }

    public static void register() {
        for (Map.Entry<String, OrganType> organType : ORGAN_TYPE_MAP.entrySet()) {
            CWRegistry.ORGAN_TYPES.register(new ResourceLocation(MODID, organType.getKey()), organType.getValue());
        }
    }
}
