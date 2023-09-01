package flaxbeard.cyberware.common.packet;

import dev.architectury.networking.NetworkManager;
import flaxbeard.cyberware.client.screen.SurgeryScreen;
import flaxbeard.cyberware.common.data.PlayerOrgansData;
import flaxbeard.cyberware.mixininterfaces.IPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

import java.util.function.Supplier;

public class OpenSurgeryGuiPacket {
    public PlayerOrgansData data;
    public OpenSurgeryGuiPacket(FriendlyByteBuf buf) {
        data = new PlayerOrgansData();
        data.readAdditionalSaveData(buf.readNbt());
    }

    public OpenSurgeryGuiPacket(PlayerOrgansData data) {
        this.data = data;
    }

    public OpenSurgeryGuiPacket(Player player) {
        this.data = ((IPlayer) player).getOrgansData();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeNbt(data.writeToNbt());
    }

    public void apply(Supplier<NetworkManager.PacketContext> contextSupplier) {
        contextSupplier.get().queue(
                () -> Minecraft.getInstance().setScreen(new SurgeryScreen(data))
        );
    }
}
