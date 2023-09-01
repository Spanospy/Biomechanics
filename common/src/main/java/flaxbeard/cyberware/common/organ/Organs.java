package flaxbeard.cyberware.common.organ;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Organs {
    public static Map<ResourceLocation, Organ> ORGANS = new HashMap<>();

    public static void register(){
        for (Map.Entry<ResourceLocation, Organ> entry : ORGANS.entrySet()) {
            Organ organ = entry.getValue();
            /*
            ITEMS.register(entry.getKey(),
                    () -> new OrganItem(organ, new Item.Properties().stacksTo(organ.max).arch$tab(CWCreativeTabs.ORGANS_TAB))
            ).get();
             */
        }
    }

    public static Organ get(ResourceLocation resourceLocation){
        return ORGANS.get(resourceLocation);
    }

    public static ResourceLocation getRegistryName(Organ organ){
        for (Map.Entry<ResourceLocation, Organ> entry : ORGANS.entrySet()) {
            if (entry.getValue().equals(organ)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void register(ResourceLocation resourceLocation, Organ organ){
        ORGANS.put(resourceLocation, organ);
    }

    public static class Organ {
        public OrganSlots slot;
        public int max;
        public List<Organ> required;
        public List<Organ> incompatible;

        public Organ(OrganSlots slots, int max, Organ[] requiredOrgans, Organ[] incompatibleOrgans){
            this.slot = slots;
            this.max = max;
            this.required = Arrays.stream(requiredOrgans).toList();
            this.incompatible = Arrays.stream(incompatibleOrgans).toList();
        }
        public void tick(Player player){};
    }
}
