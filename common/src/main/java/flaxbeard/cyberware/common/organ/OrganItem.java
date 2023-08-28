package flaxbeard.cyberware.common.organ;

import flaxbeard.cyberware.Cyberware;
import net.minecraft.world.item.Item;

public class OrganItem extends Item {
    public Organ organ;
    public OrganItem(Organ organ, Properties properties) {
        super(properties);
        this.organ = organ;
    }
}
