package prelude.network.packets.outbound;

import prelude.network.OutboundPacket;
import prelude.network.OutboundPacketBuilder;

import java.util.Objects;

public class ModInitPacket extends OutboundPacket {
    public ModInitPacket() {
        super(ModInitPacket.class);
    }

    private ModInitPacket(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String serialize() {
        return GENERIC_PACKET_FORMAT
                .replace("%packet_receiver%", receiver)
                .replace("%message%", "init");
    }

    @Override
    public ModInitPacketBuilder builder() {
        return new ModInitPacketBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModInitPacket)) return false;
        ModInitPacket that = (ModInitPacket) o;
        return Objects.equals(receiver, that.receiver);
    }

    public static class ModInitPacketBuilder extends OutboundPacketBuilder<ModInitPacket> {
        private ModInitPacketBuilder() {}

        @Override
        public ModInitPacketBuilder receiver(String receiver) {
            super.receiver(receiver);
            return this;
        }

        @Override
        public ModInitPacket build() {
            return new ModInitPacket(receiver);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ModInitPacketBuilder)) return false;
            ModInitPacketBuilder that = (ModInitPacketBuilder) o;
            return Objects.equals(receiver, that.receiver);
        }
    }
}
