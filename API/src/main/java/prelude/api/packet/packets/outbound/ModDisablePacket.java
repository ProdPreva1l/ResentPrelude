package prelude.api.packet.packets.outbound;

import prelude.api.packet.OutboundPacket;
import prelude.api.packet.OutboundPacketBuilder;

import java.util.Objects;

public class ModDisablePacket extends OutboundPacket {
    public static final String RESENT_MOD_DISABLE_PACKET_FORMAT =
            "{" +
                    "\"packet_receiver\":\"%packet_receiver%\"," +
                    "\"message\":\"disable\"" +
            "}";

    private String receiver;

    public ModDisablePacket() {
        super(ModInitPacket.class);
    }

    private ModDisablePacket(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String serialize() {
        return RESENT_MOD_DISABLE_PACKET_FORMAT
                .replace("%packet_receiver%", receiver);
    }

    @Override
    public ModDisablePacketBuilder builder() {
        return new ModDisablePacketBuilder();
    }

    @Override
    public String toString() {
        return "ModDisablePacket:" + serialize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModDisablePacket)) return false;
        ModDisablePacket that = (ModDisablePacket) o;
        return Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(receiver);
    }

    public static class ModDisablePacketBuilder extends OutboundPacketBuilder<ModDisablePacket> {
        private String receiver;

        private ModDisablePacketBuilder() {}

        public ModDisablePacketBuilder setReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        @Override
        public ModDisablePacket build() {
            return new ModDisablePacket(receiver);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ModDisablePacketBuilder)) return false;
            ModDisablePacketBuilder that = (ModDisablePacketBuilder) o;
            return Objects.equals(receiver, that.receiver);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(receiver);
        }

        @Override
        public String toString() {
            return "ModDisablePacketBuilder:" + "{" + receiver + "}";
        }
    }
}
