package flaxbeard.cyberware.common.entity;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import flaxbeard.cyberware.registry.SpawnPlacementRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;

import static flaxbeard.cyberware.Cyberware.MANAGER;
import static flaxbeard.cyberware.Cyberware.MODID;

public class CWEntities {
    public static Registrar<EntityType<?>> ENTITIES = MANAGER.get().get(Registries.ENTITY_TYPE);

    public static RegistrySupplier<EntityType<CyberZombie>> CYBER_ZOMBIE =
            ENTITIES.register(
                    new ResourceLocation(MODID, "cyber_zombie"),
                    () -> EntityType.Builder
                            .of(CyberZombie::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .clientTrackingRange(8)
                            .build(new ResourceLocation(MODID, "cyber_zombie").toString())
            );

    public static RegistrySupplier<EntityType<BruteCyberZombie>> BRUTE_CYBER_ZOMBIE =
            ENTITIES.register(
                    new ResourceLocation(MODID, "brute_cyber_zombie"),
                    () -> EntityType.Builder
                            .of(BruteCyberZombie::new, MobCategory.MONSTER)
                            .sized(0.6F *1.5f, 1.95F *1.5f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(MODID, "brute_cyber_zombie").toString())
            );

    public static void init(){
        EntityAttributeRegistry.register(CYBER_ZOMBIE, CyberZombie::createAttributes);
        EntityAttributeRegistry.register(BRUTE_CYBER_ZOMBIE, BruteCyberZombie::createAttributes);

        SpawnPlacementRegistry.register(CYBER_ZOMBIE, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Zombie::checkMobSpawnRules);
        SpawnPlacementRegistry.register(BRUTE_CYBER_ZOMBIE, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Zombie::checkMobSpawnRules);
    }
}
