package flaxbeard.cyberware;

import flaxbeard.cyberware.client.renderer.entity.CyberZombieRenderer;
import flaxbeard.cyberware.common.entity.CWEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CyberwareFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(CWEntities.CYBER_ZOMBIE.get(), CyberZombieRenderer::new);
    }
}
