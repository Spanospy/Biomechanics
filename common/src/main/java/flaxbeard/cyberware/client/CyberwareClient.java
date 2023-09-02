package flaxbeard.cyberware.client;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import flaxbeard.cyberware.api.registry.OrganRegistry;
import flaxbeard.cyberware.client.creativetab.CWCreativeTabs;
import flaxbeard.cyberware.client.renderer.entity.BruteCyberZombieRenderer;
import flaxbeard.cyberware.client.renderer.entity.CyberZombieRenderer;
import flaxbeard.cyberware.common.entity.CWEntities;
import net.minecraft.world.item.ItemStack;

public class CyberwareClient {
    public static void init() {
        EntityRendererRegistry.register(CWEntities.CYBER_ZOMBIE, CyberZombieRenderer::new);
        EntityRendererRegistry.register(CWEntities.BRUTE_CYBER_ZOMBIE, BruteCyberZombieRenderer::new);

        CreativeTabRegistry.appendStack(CWCreativeTabs.ORGANS_TAB.getKey(), OrganRegistry.getOrganItems().toArray(new ItemStack[0]));
    }
}