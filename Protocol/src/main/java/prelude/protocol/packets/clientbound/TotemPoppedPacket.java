package prelude.protocol.packets.clientbound;

import lombok.Builder;
import prelude.protocol.ClientBoundPacket;

import java.util.Objects;

@Builder
public class TotemPoppedPacket extends ClientBoundPacket {
    private String receiver;

    @Override
    public String serialize() {
        return GENERIC_PACKET_FORMAT
                .replace("%packet_receiver%", receiver)
                .replace("%message%", "totem_consumed");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TotemPoppedPacket)) return false;
        TotemPoppedPacket that = (TotemPoppedPacket) o;
        return Objects.equals(receiver, that.receiver);
    }
}
