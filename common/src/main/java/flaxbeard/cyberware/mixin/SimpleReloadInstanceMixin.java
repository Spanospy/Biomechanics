package flaxbeard.cyberware.mixin;

import flaxbeard.cyberware.Cyberware;
import flaxbeard.cyberware.api.registry.OrganRegistry;
import flaxbeard.cyberware.api.registry.OrganSlotRegistry;
import flaxbeard.cyberware.api.registry.OrganTypeRegistry;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ReloadInstance;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleReloadInstance;
import net.minecraft.util.Unit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(SimpleReloadInstance.class)
public class SimpleReloadInstanceMixin {
    @Inject(method = "create", at = @At("RETURN"))
    private static void onReloadComplete(ResourceManager resourceManager, List<PreparableReloadListener> list, Executor executor, Executor executor2, CompletableFuture<Unit> completableFuture, boolean bl, CallbackInfoReturnable<ReloadInstance> cir) {
        OrganSlotRegistry.register();
        OrganTypeRegistry.register();
        OrganRegistry.register();
    }
}
