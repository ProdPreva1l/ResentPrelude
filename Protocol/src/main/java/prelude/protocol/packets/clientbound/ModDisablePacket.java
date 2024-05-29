package prelude.protocol.packets.clientbound;

import lombok.Builder;
import prelude.protocol.ClientBoundPacket;

import java.util.Objects;

@Builder
public class ModDisablePacket extends ClientBoundPacket {
    private String receiver;

    @Override
    public String serialize() {
        return GENERIC_PACKET_FORMAT
                .replace("%packet_receiver%", receiver)
                .replace("%message%", "disable");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModDisablePacket)) return false;
        ModDisablePacket that = (ModDisablePacket) o;
        return Objects.equals(receiver, that.receiver);
    }
}
