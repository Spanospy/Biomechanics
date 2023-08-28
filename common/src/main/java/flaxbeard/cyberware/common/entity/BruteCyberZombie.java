package flaxbeard.cyberware.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class BruteCyberZombie extends CyberZombie {
    public BruteCyberZombie(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }
}
