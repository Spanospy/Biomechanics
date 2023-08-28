package flaxbeard.cyberware.client.renderer.entity.layers;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

import static flaxbeard.cyberware.Cyberware.MODID;

public class BruteCyberZombieLayer <T extends Zombie, M extends ZombieModel<T>> extends EyesLayer<T, M> {
    public BruteCyberZombieLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }
    private static final RenderType HIGHTLIGHT = RenderType.eyes(new ResourceLocation(MODID, "textures/entity/brute_cyber_zombie_highlight.png"));

    @Override
    public RenderType renderType() {
        return HIGHTLIGHT;
    }
}
