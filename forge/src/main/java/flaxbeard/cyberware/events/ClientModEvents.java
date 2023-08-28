package flaxbeard.cyberware.events;

import flaxbeard.cyberware.client.renderer.entity.CyberZombieRenderer;
import flaxbeard.cyberware.common.entity.CWEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static flaxbeard.cyberware.Cyberware.MODID;

@Mod.EventBusSubscriber(
        modid = MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(CWEntities.CYBER_ZOMBIE.get(), CyberZombieRenderer::new);
    }
}
