package flaxbeard.cyberware.mixin;

import dev.architectury.registry.CreativeTabRegistry;
import flaxbeard.cyberware.api.registry.OrganRegistry;
import flaxbeard.cyberware.api.registry.OrganSlotRegistry;
import flaxbeard.cyberware.api.registry.OrganTypeRegistry;
import flaxbeard.cyberware.client.creativetab.CWCreativeTabs;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ReloadInstance;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleReloadInstance;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemStack;
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

        CreativeTabRegistry.appendStack(CWCreativeTabs.ORGANS_TAB.getKey(), OrganRegistry.getOrganItems().toArray(new ItemStack[0]));
    }
}