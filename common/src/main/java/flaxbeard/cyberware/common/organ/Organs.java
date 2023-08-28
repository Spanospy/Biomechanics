package flaxbeard.cyberware.common.organ;

import flaxbeard.cyberware.common.organ.biological.HeartOrgan;
import flaxbeard.cyberware.common.organ.cybernetic.CyberHeartOrgan;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import static flaxbeard.cyberware.Cyberware.ITEMS;
import static flaxbeard.cyberware.Cyberware.MODID;

public class Organs {
    public static Map<String, Organ> organMap = new HashMap<>();

    public static final Organ HEART = new HeartOrgan();
    public static final Organ CYBER_HEART = new CyberHeartOrgan();

    public static void register(){
        for (Organ organ : organMap.values()) {
            ITEMS.register(new ResourceLocation(MODID, organ.name), () -> new OrganItem(organ));
        }
    }

    public static Organ getOrgan(String name){
        return organMap.get(name);
    }
}
