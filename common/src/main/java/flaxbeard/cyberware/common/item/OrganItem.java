package flaxbeard.cyberware.common.item;

import flaxbeard.cyberware.api.Organ;
import net.minecraft.world.item.Item;

public class OrganItem extends Item {

    public Organ organ;
    public OrganItem(Properties properties, Organ organ) {
        super(properties);
        this.organ = organ;
    }
}
