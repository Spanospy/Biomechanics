package flaxbeard.cyberware.mixin;

import flaxbeard.cyberware.common.CWDamageTypes;
import flaxbeard.cyberware.common.organ.Organ;
import flaxbeard.cyberware.common.organ.Organs;
import flaxbeard.cyberware.interfaces.CyberwareAbilities;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Shadow public abstract void die(DamageSource damageSource);

    @Shadow public abstract boolean isCreative();

    @Shadow public abstract Abilities getAbilities();

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        if((Object)this instanceof ServerPlayer){
            CyberwareAbilities abilities = (CyberwareAbilities) this.getAbilities();
            for (Organ organ : abilities.getCyberwares()) {
                organ.tick((Player) (Object) this);
            }
            if (!abilities.hasCyberware(Organs.CYBER_HEART) && abilities.hasCyberware(Organs.HEART) && !this.isCreative()) {
                this.die(new DamageSource(new Holder.Direct<>(CWDamageTypes.NO_HEART.get())));
            }
        }
    }
}
