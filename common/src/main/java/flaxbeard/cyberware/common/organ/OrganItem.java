package flaxbeard.cyberware.common.organ;

import net.minecraft.world.item.Item;

public class OrganItem extends Item {
    public Organ organ;
    public OrganItem(Organ organ) {
        super(new Properties());
        this.organ = organ;
    }
}
