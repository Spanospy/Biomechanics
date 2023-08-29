package flaxbeard.cyberware.common.organ;

import flaxbeard.cyberware.client.creativetab.CWCreativeTabs;
import flaxbeard.cyberware.common.organ.biological.HeartOrgan;
import flaxbeard.cyberware.common.organ.cybernetic.CyberHeartOrgan;
import flaxbeard.cyberware.common.organ.cybernetic.SalvagedCyberHeartOrgan;
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
    public static final Organ SALVAGED_CYBER_HEART = new SalvagedCyberHeartOrgan();

    public static void register(){
        for (Organ organ : organMap.values()) {
            organ.item = ITEMS.register(new ResourceLocation(MODID, organ.name),
                    () -> new OrganItem(organ, new Item.Properties().stacksTo(organ.maxUpgrades).arch$tab(CWCreativeTabs.ORGANS_TAB))
            ).get();
        }
    }

    public static Organ getOrgan(String name){
        return organMap.get(name);
    }
}
