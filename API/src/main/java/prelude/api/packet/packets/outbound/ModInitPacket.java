package prelude.api.packet.packets.outbound;

import prelude.api.packet.OutboundPacket;
import prelude.api.packet.OutboundPacketBuilder;

import java.util.Objects;

public class ModInitPacket extends OutboundPacket {
    public static final String RESENT_MOD_INIT_PACKET_FORMAT =
            "{" +
                    "\"packet_receiver\":\"%packet_receiver%\"," +
                    "\"message\":\"init\"" +
            "}";

    private String receiver;

    public ModInitPacket() {
        super(ModInitPacket.class);
    }

    private ModInitPacket(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String serialize() {
        return RESENT_MOD_INIT_PACKET_FORMAT
                .replace("%packet_receiver%", receiver);
    }

    @Override
    public ModInitPacketBuilder builder() {
        return new ModInitPacketBuilder();
    }

    @Override
    public String toString() {
        return "ModInitPacket:" + serialize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModInitPacket)) return false;
        ModInitPacket that = (ModInitPacket) o;
        return Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(receiver);
    }

    public static class ModInitPacketBuilder extends OutboundPacketBuilder<ModInitPacket> {
        private String receiver;

        private ModInitPacketBuilder() {}

        public ModInitPacketBuilder setReceiver(String receiver) {
            this.receiver = receiver;
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

        @Override
        public int hashCode() {
            return Objects.hashCode(receiver);
        }

        @Override
        public String toString() {
            return "ModInitPacketBuilder:" + "{" + receiver + "}";
        }
    }
}
