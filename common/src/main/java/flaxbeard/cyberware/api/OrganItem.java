package flaxbeard.cyberware.api;

import flaxbeard.cyberware.api.organ.BiologicalOrgan;
import flaxbeard.cyberware.api.organ.ManufacturedOrgan;
import flaxbeard.cyberware.api.organ.Organ;
import flaxbeard.cyberware.api.organ.SalvagedOrgan;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class OrganItem extends Item {
    private Organ organ;
    public OrganItem(Properties properties, Organ organ) {
        super(properties);
        this.organ = organ;
    }

    /**
     * Creates an OrganItem from an Organ with default properties
    **/
    public static OrganItem of(Organ organ) {
        return new OrganItem(new Properties().stacksTo(organ.getMax()), organ);
    }

    public Organ getOrgan() {
        return organ;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(organ.getTooltip());
        if (organ instanceof BiologicalOrgan)
            list.add(Component.translatable("cyberware.tooltip.biological"));
        else if (organ instanceof SalvagedOrgan)
            list.add(Component.translatable("cyberware.tooltip.salvaged"));
        else if (organ instanceof ManufacturedOrgan)
            list.add(Component.translatable("cyberware.tooltip.manufactured"));
        super.appendHoverText(itemStack, level, list, tooltipFlag);
    }
}
