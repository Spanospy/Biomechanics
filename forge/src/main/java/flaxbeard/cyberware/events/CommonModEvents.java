package flaxbeard.cyberware.events;

import flaxbeard.cyberware.common.entity.CWEntities;
import flaxbeard.cyberware.common.entity.CyberZombie;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static flaxbeard.cyberware.Cyberware.MODID;

@Mod.EventBusSubscriber(
        modid = MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class CommonModEvents {
    @SubscribeEvent
    public static void registerEntityAttribute(SpawnPlacementRegisterEvent event){
        event.register(
                CWEntities.CYBER_ZOMBIE.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                Zombie::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.OR
        );
    }
}
