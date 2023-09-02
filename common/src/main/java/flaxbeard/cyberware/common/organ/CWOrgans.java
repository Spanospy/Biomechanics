package flaxbeard.cyberware.common.organ;

import dev.architectury.registry.registries.RegistrySupplier;
import flaxbeard.cyberware.api.OrganItem;
import flaxbeard.cyberware.api.organ.Organ;
import flaxbeard.cyberware.api.registry.OrganRegistry;
import flaxbeard.cyberware.client.creativetab.CWCreativeTabs;
import flaxbeard.cyberware.common.item.CWItems;
import flaxbeard.cyberware.common.organ.type.CWOrganTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

import static flaxbeard.cyberware.Cyberware.MODID;

public class CWOrgans {
    public static Map<Organ, RegistrySupplier<Item>> ORGANITEMS = new HashMap<>();

    public static final Organ HEART = register(
            "heart",
            new Organ(
                    CWOrganTypes.HEART,
                    1,
                    null,
                    null
            )
    );

    public static final Organ EYES = register(
            "eyes",
            new Organ(
                    CWOrganTypes.EYES,
                    1,
                    null,
                    null
            )
    );

    public static final Organ BRAIN = register(
            "brain",
            new Organ(
                    CWOrganTypes.BRAIN,
                    1,
                    null,
                    null
            )
    );


    public static Organ register(String id, Organ organ) {
        OrganRegistry.register(new ResourceLocation(MODID, id), organ);
        System.out.println("Registered organ " + id);
        ORGANITEMS.put(
                organ,
                CWItems.register(id, new OrganItem(new Item.Properties().stacksTo(organ.getMax()).arch$tab(CWCreativeTabs.ORGANS_TAB), organ))
        );
        return organ;
    }
    public static void register() {}
}
