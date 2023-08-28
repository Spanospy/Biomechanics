package flaxbeard.cyberware.client.renderer.entity;

import flaxbeard.cyberware.client.renderer.entity.layers.CyberZombieLayer;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

import static flaxbeard.cyberware.Cyberware.MODID;

public class CyberZombieRenderer extends AbstractZombieRenderer<Zombie, ZombieModel<Zombie>> {

    public CyberZombieRenderer(EntityRendererProvider.Context context) {
        this(context, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
    }
    public CyberZombieRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayerLocation, ModelLayerLocation modelLayerLocation2, ModelLayerLocation modelLayerLocation3) {
        super(context, new ZombieModel(context.bakeLayer(modelLayerLocation)), new ZombieModel(context.bakeLayer(modelLayerLocation2)), new ZombieModel(context.bakeLayer(modelLayerLocation3)));
        this.addLayer(new CyberZombieLayer(this));
    }

    private static final ResourceLocation CYBER_ZOMBIE_LOCATION = new ResourceLocation(MODID, "textures/entity/cyber_zombie.png");
    @Override
    public ResourceLocation getTextureLocation(Zombie zombie) {
        return CYBER_ZOMBIE_LOCATION;
    }
}
