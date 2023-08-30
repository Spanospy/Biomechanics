package flaxbeard.cyberware.registry;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static flaxbeard.cyberware.Cyberware.MODID;

public class SpawnPlacementRegistryImpl{
    private static final List<EntityType> entityTypes = new ArrayList<>();
    private static final List<SpawnPlacements.Type> types = new ArrayList<>();
    private static final List<Heightmap.Types> heightmapTypes = new ArrayList<>();
    private static final List<SpawnPlacements.SpawnPredicate> spawnPredicates = new ArrayList<>();

    public static <T extends Mob> void register(Supplier<? extends EntityType<T>> entityType, SpawnPlacements.Type type, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<T> spawnPredicate) {
        entityTypes.add(entityType.get());
        types.add(type);
        heightmapTypes.add(heightmapType);
        spawnPredicates.add(spawnPredicate);
    }

    static {
        EventBuses.onRegistered(MODID, bus ->{
            bus.register(SpawnPlacementRegistryImpl.class);
        });
    }

    @SubscribeEvent
    public static void registerEntityAttribute(SpawnPlacementRegisterEvent event){
        for (int i = 0; i < entityTypes.size(); i++) {
            event.register(
                    entityTypes.get(i),
                    types.get(i),
                    heightmapTypes.get(i),
                    spawnPredicates.get(i),
                    SpawnPlacementRegisterEvent.Operation.OR
            );
        }
    }
}
