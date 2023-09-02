package flaxbeard.cyberware.mixin;

import flaxbeard.cyberware.api.playerdata.OrganPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract boolean isUsingItem();

    @Shadow public abstract ItemStack getItemInHand(InteractionHand interactionHand);

    @Shadow public abstract InteractionHand getUsedItemHand();

    @Inject(method = "updatingUsingItem", at = @At("HEAD"), cancellable = true)
    private void updatingUsingItem(CallbackInfo info) {
        /*if (((LivingEntity)(Object)this) instanceof Player player) {
            if (this.isUsingItem()) {
                if (((OrganPlayer) player).getOrgansData().hasOrganType(CWOrganTypes.STOMACH) && this.getItemInHand(this.getUsedItemHand()).isEdible()) {
                    info.cancel();
                }
            }
        }*/
    }

    @ModifyVariable(method = "hurt", at = @At("HEAD"), argsOnly = true)
    private float hurt(float value) {
        /*if (this instanceof OrganPlayer organPlayer)
            if (!organPlayer.getOrgansData().hasOrganType(CWOrganTypes.SKIN))
                return value * 1.75F;*/
        return value;
    }
}
