package prelude.api;

import prelude.network.PacketManager;
import prelude.network.packets.outbound.ModDisablePacket;
import prelude.network.packets.outbound.ModInitPacket;

import static prelude.network.packets.outbound.ModDisablePacket.*;
import static prelude.network.packets.outbound.ModInitPacket.*;

public abstract class ResentMod {
    protected boolean enabled = false;

    protected ResentMod() {
    }

    public void initMod(PreludePlayer preludePlayer) {
        ModInitPacketBuilder builder = (ModInitPacketBuilder)
                PacketManager.getOutboundPacketBuilder(ModInitPacket.class);

        preludePlayer.sendPacket(builder.receiver(this.getReceiverId()).build());
    }

    public void disableMod(PreludePlayer preludePlayer) {
        ModDisablePacketBuilder builder = (ModDisablePacketBuilder)
                PacketManager.getOutboundPacketBuilder(ModDisablePacket.class);

        preludePlayer.sendPacket(builder.receiver(this.getReceiverId()).build());
    }

    public abstract String getReceiverId();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public abstract boolean isAllowed();

    public boolean isOfficiallyHooked() {
        return false;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
