package flaxbeard.cyberware.common.item;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import flaxbeard.cyberware.client.creativetab.CWCreativeTabs;
import flaxbeard.cyberware.common.block.CWBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWItems {
    public static final Registrar<Item> ITEMS = MANAGER.get().get(Registries.ITEM);

    public static final RegistrySupplier<Item> SURGERY_MACHINE = register(
            "surgery_machine",
            new BlockItem(CWBlocks.SURGERY_MACHINE.get(), new Item.Properties().arch$tab(CWCreativeTabs.OTHER_TAB))
    );
    public static final RegistrySupplier<Item> ORGAN = register(
            "organ",
            new OrganItem(new Item.Properties())
    );

    public static RegistrySupplier<Item> register(String id, Item item) {
        return ITEMS.register(new ResourceLocation(MODID, id), () -> item);
    }

    public static void register() {}
}
