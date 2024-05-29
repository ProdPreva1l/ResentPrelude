package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;
import prelude.protocol.packets.clientbound.TotemPoppedPacket;
import prelude.protocol.packets.clientbound.TotemPoppedPacket.TotemPoppedPacketBuilder;

public abstract class TotemTweaks extends ResentMod {
    protected TotemTweaks() {
        super();
    }

    public void sendTotemPoppedEvent(PreludePlayer preludePlayer) {
        TotemPoppedPacketBuilder builder = TotemPoppedPacket.builder();

        preludePlayer.sendPacket(builder.receiver(this.getReceiverId()).build());
    }

    @Override
    public final String getReceiverId() {
        return "totem_tweaks";
    }
}
