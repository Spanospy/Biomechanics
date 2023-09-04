package flaxbeard.cyberware.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import flaxbeard.cyberware.client.renderer.entity.BruteCyberZombieRenderer;
import flaxbeard.cyberware.client.renderer.entity.CyberZombieRenderer;
import flaxbeard.cyberware.common.entity.CWEntities;

public class CyberwareClient {
    public static void init() {
        EntityRendererRegistry.register(CWEntities.CYBER_ZOMBIE, CyberZombieRenderer::new);
        EntityRendererRegistry.register(CWEntities.BRUTE_CYBER_ZOMBIE, BruteCyberZombieRenderer::new);
    }
}