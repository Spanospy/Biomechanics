package flaxbeard.cyberware.client;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import flaxbeard.cyberware.common.organ.Organs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWCreativeTabs {
    public static final Registrar<CreativeModeTab> CREATIVE_MODE_TABS = MANAGER.get().get(Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> SALVAGED_TAB = CREATIVE_MODE_TABS.register(new ResourceLocation(MODID, "salvaged_tab"),
            () -> new CreativeModeTab.Builder(CreativeModeTab.Row.BOTTOM, 0).icon(
                    () -> new ItemStack(Organs.CYBER_HEART.salvagedItem))
                    .title(Component.translatable("cyberware.creative_tab.salvaged")).build()
    );

    public static final RegistrySupplier<CreativeModeTab> MANUFACTURED_TAB = CREATIVE_MODE_TABS.register(new ResourceLocation(MODID, "manufactured_tab"),
            () -> new CreativeModeTab.Builder(CreativeModeTab.Row.BOTTOM, 0).icon(
                    () -> new ItemStack(Organs.CYBER_HEART.item)
            ).title(Component.translatable("cyberware.creative_tab.manufactured")).build()
    );

    public static final RegistrySupplier<CreativeModeTab> BIOLOGICAL_TAB = CREATIVE_MODE_TABS.register(new ResourceLocation(MODID, "biological_tab"),
            () -> CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0).icon(
                    () -> new ItemStack(Organs.HEART.item)
            ).title(Component.translatable("cyberware.creative_tab.biological")).build()
    );
}
