package flaxbeard.cyberware.client.screen;

import flaxbeard.cyberware.common.data.PlayerOrgansData;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SurgeryScreen extends Screen {
    PlayerOrgansData actualOrgans;

    public SurgeryScreen(PlayerOrgansData data) {
        super(Component.translatable("cyberware.gui.surgery"));
        this.actualOrgans = data;
    }

    @Override
    public void init(){
        super.init();
    }
}
