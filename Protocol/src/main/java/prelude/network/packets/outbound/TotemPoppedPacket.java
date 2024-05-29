package prelude.network.packets.outbound;

import prelude.network.OutboundPacket;
import prelude.network.OutboundPacketBuilder;

import java.util.Objects;

public class TotemPoppedPacket extends OutboundPacket {
    public TotemPoppedPacket() {
        super(TotemPoppedPacket.class);
    }

    private TotemPoppedPacket(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String serialize() {
        return GENERIC_PACKET_FORMAT
                .replace("%packet_receiver%", receiver)
                .replace("%message%", "totem_consumed");
    }

    @Override
    public TotemPoppedPacketBuilder builder() {
        return new TotemPoppedPacketBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TotemPoppedPacket)) return false;
        TotemPoppedPacket that = (TotemPoppedPacket) o;
        return Objects.equals(receiver, that.receiver);
    }

    public static class TotemPoppedPacketBuilder extends OutboundPacketBuilder<TotemPoppedPacket> {
        private TotemPoppedPacketBuilder() {}

        @Override
        public TotemPoppedPacketBuilder receiver(String receiver) {
            super.receiver(receiver);
            return this;
        }

        @Override
        public TotemPoppedPacket build() {
            return new TotemPoppedPacket(receiver);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TotemPoppedPacketBuilder)) return false;
            TotemPoppedPacketBuilder that = (TotemPoppedPacketBuilder) o;
            return Objects.equals(receiver, that.receiver);
        }
    }
}
