package flaxbeard.cyberware.client.screen;

import flaxbeard.cyberware.api.playerdata.PlayerOrgansData;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import static flaxbeard.cyberware.Cyberware.MODID;

public class SurgeryScreen extends Screen {
    private PlayerOrgansData actualOrgans;
    private static final ResourceLocation BACKGROUND = new ResourceLocation(MODID, "textures/gui/surgery_machine.png");

    public SurgeryScreen(PlayerOrgansData data) {
        super(Component.translatable("cyberware.gui.surgery"));
        this.actualOrgans = data;
    }

    @Override
    public void init(){
        super.init();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
