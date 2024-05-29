package prelude.protocol.packets.clientbound;

import lombok.Builder;
import prelude.protocol.ClientBoundPacket;

import java.util.Objects;

@Builder
public class ModInitPacket extends ClientBoundPacket {
    private String receiver;

    @Override
    public String serialize() {
        return GENERIC_PACKET_FORMAT
                .replace("%packet_receiver%", receiver)
                .replace("%message%", "init");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModInitPacket)) return false;
        ModInitPacket that = (ModInitPacket) o;
        return Objects.equals(receiver, that.receiver);
    }
}
