package flaxbeard.cyberware.common.organ;

import flaxbeard.cyberware.api.Organ;
import flaxbeard.cyberware.api.registry.CWRegistry;
import flaxbeard.cyberware.common.organ.type.CWOrganType;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;

import static flaxbeard.cyberware.Cyberware.MODID;

public class CWOrgan {
    public static final HashMap<String, Organ> ORGAN_MAP = new HashMap<>();

    public static final Organ HEART = register("heart", new Organ(
            CWOrganType.HEART,
            1,
            null,
            null,
            null,
            0
    ));

    public static Organ register(String id, Organ organ) {
        ORGAN_MAP.put(id, organ);
        return organ;
    }

    public static void register() {
        for (HashMap.Entry<String, Organ> organ : ORGAN_MAP.entrySet()) {
            CWRegistry.ORGANS.register(new ResourceLocation(MODID, organ.getKey()), organ.getValue());
        }
    }
}
