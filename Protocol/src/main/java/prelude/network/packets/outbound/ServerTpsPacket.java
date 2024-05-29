package prelude.network.packets.outbound;

import prelude.network.OutboundPacket;
import prelude.network.OutboundPacketBuilder;

import java.util.Objects;


public class ServerTpsPacket extends OutboundPacket {
    private double tps;

    public ServerTpsPacket() {
        super(ServerTpsPacket.class);
    }

    private ServerTpsPacket(double tps, String receiver) {
        this.tps = tps;
    }

    @Override
    public String serialize() {
        return GENERIC_PACKET_FORMAT
                .replace("%receiver%", receiver)
                .replace("%message%", String.valueOf(tps));
    }

    @Override
    public ServerTpsPacketBuilder builder() {
        return new ServerTpsPacketBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerTpsPacket)) return false;
        ServerTpsPacket that = (ServerTpsPacket) o;
        return tps == that.tps && Objects.equals(receiver, that.receiver);
    }

    public static class ServerTpsPacketBuilder extends OutboundPacketBuilder<ServerTpsPacket> {
        private double tps;

        private ServerTpsPacketBuilder() {}

        public ServerTpsPacketBuilder tps(double tps) {
            this.tps = tps;
            return this;
        }

        @Override
        public ServerTpsPacketBuilder receiver(String receiver) {
            super.receiver(receiver);
            return this;
        }

        @Override
        public ServerTpsPacket build() {
            return new ServerTpsPacket(tps, receiver);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ServerTpsPacketBuilder)) return false;
            ServerTpsPacketBuilder that = (ServerTpsPacketBuilder) o;
            return tps == that.tps && Objects.equals(receiver, that.receiver);
        }
    }
}
