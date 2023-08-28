package flaxbeard.cyberware.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import flaxbeard.cyberware.client.renderer.entity.layers.BruteCyberZombieLayer;
import flaxbeard.cyberware.common.entity.BruteCyberZombie;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static flaxbeard.cyberware.Cyberware.MODID;

public class BruteCyberZombieRenderer extends MobRenderer<BruteCyberZombie, HumanoidModel<BruteCyberZombie>> {
    private static final ResourceLocation BRUTE_CYBER_ZOMBIE_LOCATION = new ResourceLocation(MODID, "textures/entity/brute_cyber_zombie.png");
    public BruteCyberZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieModel<>(
                context.bakeLayer(ModelLayers.ZOMBIE)
        ), 1.5F);
        this.addLayer(new BruteCyberZombieLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(BruteCyberZombie entity) {
        return BRUTE_CYBER_ZOMBIE_LOCATION;
    }

    protected void scale(BruteCyberZombie giant, PoseStack poseStack, float f) {
        poseStack.scale(1.5f, 1.5f, 1.5f);
    }
}
