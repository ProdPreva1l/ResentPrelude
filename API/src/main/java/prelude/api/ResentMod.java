package prelude.api;

import lombok.Getter;
import prelude.protocol.packets.clientbound.ModDisablePacket;
import prelude.protocol.packets.clientbound.ModInitPacket;

import static prelude.protocol.packets.clientbound.ModDisablePacket.ModDisablePacketBuilder;
import static prelude.protocol.packets.clientbound.ModInitPacket.ModInitPacketBuilder;

@Getter
public abstract class ResentMod {
    protected boolean enabled = false;

    protected ResentMod() {
    }

    public void initMod(PreludePlayer preludePlayer) {
        ModInitPacketBuilder builder = ModInitPacket.builder();

        preludePlayer.sendPacket(builder.receiver(this.getReceiverId()).build());
    }

    public void disableMod(PreludePlayer preludePlayer) {
        ModDisablePacketBuilder builder = ModDisablePacket.builder();

        preludePlayer.sendPacket(builder.receiver(this.getReceiverId()).build());
    }

    public abstract String getReceiverId();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public abstract boolean isAllowed();

    public boolean isOfficiallyHooked() {
        return false;
    }
}
