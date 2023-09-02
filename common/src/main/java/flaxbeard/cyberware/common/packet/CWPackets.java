package flaxbeard.cyberware.common.packet;

import dev.architectury.networking.NetworkChannel;
import net.minecraft.resources.ResourceLocation;

import static flaxbeard.cyberware.Cyberware.MODID;

public class CWPackets {
    public static final NetworkChannel CHANNEL = NetworkChannel.create(new ResourceLocation(MODID, "network"));

    public static void register(){
        CHANNEL.register(OpenSurgeryGuiPacket.class, OpenSurgeryGuiPacket::encode, OpenSurgeryGuiPacket::new, OpenSurgeryGuiPacket::apply);
    }
}
