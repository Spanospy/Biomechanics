package flaxbeard.cyberware.common.entity;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.biome.MobSpawnSettings;
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
                            .sized(0.6F *1.25f, 1.95F *1.25f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(MODID, "brute_cyber_zombie").toString())
            );

    public static void register() {
        EntityAttributeRegistry.register(CYBER_ZOMBIE, CyberZombie::createAttributes);
        EntityAttributeRegistry.register(BRUTE_CYBER_ZOMBIE, BruteCyberZombie::createAttributes);

        SpawnPlacementsRegistry.register(CYBER_ZOMBIE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Zombie::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(BRUTE_CYBER_ZOMBIE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Zombie::checkMobSpawnRules);

        BiomeModifications.addProperties(
                (biomeContext, properties) -> properties.getSpawnProperties().addSpawn(
                        MobCategory.MONSTER,
                        new MobSpawnSettings.SpawnerData(
                                CWEntities.CYBER_ZOMBIE.get(),
                                13,
                                2,
                                8
                        )
                )
        );
        BiomeModifications.addProperties(
                (biomeContext, properties) -> properties.getSpawnProperties().addSpawn(
                        MobCategory.MONSTER,
                        new MobSpawnSettings.SpawnerData(
                                CWEntities.BRUTE_CYBER_ZOMBIE.get(),
                                6,
                                1,
                                4
                        )
                )
        );
    }
}
