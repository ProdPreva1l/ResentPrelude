package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;
import prelude.network.PacketManager;
import prelude.network.packets.outbound.TotemPoppedPacket;
import prelude.network.packets.outbound.TotemPoppedPacket.TotemPoppedPacketBuilder;

public abstract class TotemTweaks extends ResentMod {
    protected TotemTweaks() {
        super();
    }

    public void sendTotemPoppedEvent(PreludePlayer preludePlayer) {
        TotemPoppedPacketBuilder builder = (TotemPoppedPacketBuilder)
                PacketManager.getOutboundPacketBuilder(TotemPoppedPacket.class);

        preludePlayer.sendPacket(builder.receiver(this.getReceiverId()).build());
    }

    @Override
    public final String getReceiverId() {
        return "totem_tweaks";
    }
}
