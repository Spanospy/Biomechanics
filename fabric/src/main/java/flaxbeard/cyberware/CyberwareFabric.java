package flaxbeard.cyberware;

import flaxbeard.cyberware.common.entity.CWEntities;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;

public class CyberwareFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		Cyberware.init();

		SpawnPlacements.register(CWEntities.CYBER_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Zombie::checkMobSpawnRules);
	}
}
