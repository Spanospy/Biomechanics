package flaxbeard.cyberware.common.organ;

import flaxbeard.cyberware.client.CWCreativeTabs;
import flaxbeard.cyberware.common.organ.biological.HeartOrgan;
import flaxbeard.cyberware.common.organ.cybernetic.CyberHeartOrgan;
import flaxbeard.cyberware.common.organ.cybernetic.ICybernetic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

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
            if (organ instanceof ICybernetic){
                organ.item = ITEMS.register(new ResourceLocation(MODID, organ.name),
                        () -> new OrganItem(organ, new Item.Properties().arch$tab(CWCreativeTabs.MANUFACTURED_TAB))
                ).get();
                organ.salvagedItem = ITEMS.register(new ResourceLocation(MODID, "salvaged_" + organ.name),
                        () -> new OrganItem(organ, new Item.Properties().arch$tab(CWCreativeTabs.SALVAGED_TAB))
                ).get();
            }else {
                organ.item = ITEMS.register(new ResourceLocation(MODID, organ.name),
                        () -> new OrganItem(organ, new Item.Properties().arch$tab(CWCreativeTabs.BIOLOGICAL_TAB))
                ).get();
            }
        }
    }

    public static Organ getOrgan(String name){
        return organMap.get(name);
    }
}
