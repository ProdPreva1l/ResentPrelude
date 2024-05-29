package prelude.protocol.packets.clientbound;

import lombok.Builder;
import prelude.protocol.ClientBoundPacket;

import java.util.Objects;


@Builder
public class ServerTpsPacket extends ClientBoundPacket {
    private double tps;
    private String receiver;

    @Override
    public String serialize() {
        return GENERIC_PACKET_FORMAT
                .replace("%receiver%", receiver)
                .replace("%message%", String.valueOf(tps));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerTpsPacket)) return false;
        ServerTpsPacket that = (ServerTpsPacket) o;
        return tps == that.tps && Objects.equals(receiver, that.receiver);
    }
}
