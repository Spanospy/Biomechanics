package flaxbeard.cyberware.common.organ.biological;

import flaxbeard.cyberware.common.organ.Organ;
import flaxbeard.cyberware.common.organ.OrganSlots;
import flaxbeard.cyberware.common.organ.Organs;
import net.minecraft.world.entity.player.Player;

public class HeartOrgan extends Organ {
    public HeartOrgan() {
        super(OrganSlots.HEART, 1, new Organ[0], new Organ[]{Organs.CYBER_HEART}, "heart");
    }

    public HeartOrgan(OrganSlots slots, Organ[] incompatibleOrgans, String name) {
        super(slots, 1, new Organ[0], incompatibleOrgans, name);
    }

    @Override
    public void tick(Player player) {}
}
