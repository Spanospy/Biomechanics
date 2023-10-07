package flaxbeard.cyberware.client.creativetab;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import flaxbeard.cyberware.api.registry.CWRegistry;
import flaxbeard.cyberware.common.item.CWItems;
import flaxbeard.cyberware.common.item.OrganItem;
import flaxbeard.cyberware.common.organ.CWOrgan;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWCreativeTabs {
    public static final Registrar<CreativeModeTab> CREATIVE_MODE_TABS = MANAGER.get().get(Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> ORGANS_TAB = CREATIVE_MODE_TABS.register(new ResourceLocation(MODID, "organs_tab"),
            () -> new CreativeModeTab.Builder(CreativeModeTab.Row.BOTTOM, 0).icon(
                    () -> CWItems.ORGAN_ITEMS.get(CWOrgan.HEART).getDefaultInstance()
            ).title(Component.translatable("cyberware.creative_tab.organs")).build()
    );

    public static final RegistrySupplier<CreativeModeTab> OTHER_TAB = CREATIVE_MODE_TABS.register(new ResourceLocation(MODID, "other_tab"),
            () -> new CreativeModeTab.Builder(CreativeModeTab.Row.BOTTOM, 1).icon(
                    () -> new ItemStack(CWItems.SURGERY_MACHINE.get())
            ).title(Component.translatable("cyberware.creative_tab.other")).build()
    );
}
