package flaxbeard.cyberware.common.organ;

import flaxbeard.cyberware.common.organ.cybernetic.interfaces.ICybernetic;
import flaxbeard.cyberware.common.organ.cybernetic.interfaces.IPowerConsuming;
import flaxbeard.cyberware.common.organ.cybernetic.interfaces.ISalvaged;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class OrganItem extends Item{
    public Organ organ;
    public OrganItem(Organ organ, Properties properties) {
        super(properties);
        this.organ = organ;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (organ instanceof ICybernetic cybernetic) {
            if (organ instanceof ISalvaged) {
                list.add(Component.translatable("cyberware.tooltip.salvaged").withStyle(ChatFormatting.DARK_GRAY));
            } else {
                list.add(Component.translatable("cyberware.tooltip.manufactured").withStyle(ChatFormatting.GRAY));
            }
            if (organ instanceof IPowerConsuming powerConsuming) {
                float powerConsumption = powerConsuming.getPowerConsumption();
                String powerConsumptionString = String.format("%.1f", powerConsumption);
                list.add(Component.translatable("cyberware.tooltip.powerConsumption").append(powerConsumptionString + "pow/tick").withStyle(ChatFormatting.GREEN));
            }
            float toleranceCost = cybernetic.getToleranceCost();
            String toleranceCostString = String.format("%.1f", toleranceCost);
            list.add(Component.translatable("cyberware.tooltip.toleranceCost").append(toleranceCostString).withStyle(ChatFormatting.RED));
        }else {
            list.add(Component.translatable("cyberware.tooltip.biological").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
