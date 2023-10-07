package flaxbeard.cyberware.common.item;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import flaxbeard.cyberware.api.Organ;
import flaxbeard.cyberware.api.registry.CWRegistry;
import flaxbeard.cyberware.client.creativetab.CWCreativeTabs;
import flaxbeard.cyberware.common.block.CWBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWItems {
    public static final Registrar<Item> ITEMS = MANAGER.get().get(Registries.ITEM);

    public static final RegistrySupplier<Item> SURGERY_MACHINE = register(
            "surgery_machine",
            new BlockItem(CWBlocks.SURGERY_MACHINE.get(), new Item.Properties().arch$tab(CWCreativeTabs.OTHER_TAB))
    );

    public static RegistrySupplier<Item> register(String id, Item item) {
        return ITEMS.register(new ResourceLocation(MODID, id), () -> item);
    }

    public static final HashMap<Organ, OrganItem> ORGAN_ITEMS = new HashMap<>();
    public static void register() {
        for (Map.Entry<ResourceLocation, Organ> organ : CWRegistry.ORGANS.getRegistry().entrySet()) {
            OrganItem organItem = new OrganItem(new Item.Properties().arch$tab(CWCreativeTabs.ORGANS_TAB), organ.getValue());
            ORGAN_ITEMS.put(organ.getValue(), organItem);
            register(organ.getKey().getPath(), organItem);
        }
    }
}
