package prelude.network.packets.outbound;

import prelude.network.OutboundPacket;
import prelude.network.OutboundPacketBuilder;

import java.util.Objects;

public class AnchorRendererPacket extends OutboundPacket {
    public static final String ANCHOR_RENDERER_PACKET_MESSAGE_FORMAT =
            "{" +
                    "\"x\":\"%x%\"," +
                    "\"y\":\"%y%\"," +
                    "\"z\":\"%z%\"," +
                    "\"charge\":\"%charge%\"" +
            "}";

    private int x;
    private int y;
    private int z;
    private int charge;
    private String receiver;

    public AnchorRendererPacket() {
        super(AnchorRendererPacket.class);
    }

    private AnchorRendererPacket(int x, int y, int z, int charge, String receiver) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.charge = charge;
        this.receiver = receiver;
    }

    @Override
    public String serialize() {
        return GENERIC_PACKET_FORMAT
                .replace("%receiver%", receiver)
                .replace("%message%",
                        ANCHOR_RENDERER_PACKET_MESSAGE_FORMAT
                                .replace("%x%", Integer.toString(x))
                                .replace("%y%", Integer.toString(y))
                                .replace("%z%", Integer.toString(z))
                                .replace("%charge%", Integer.toString(charge))
                );
    }

    @Override
    public AnchorRendererPacketBuilder builder() {
        return new AnchorRendererPacketBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnchorRendererPacket)) return false;
        AnchorRendererPacket that = (AnchorRendererPacket) o;
        return x == that.x && y == that.y && z == that.z && charge == that.charge && Objects.equals(receiver, that.receiver);
    }

    public static class AnchorRendererPacketBuilder extends OutboundPacketBuilder<AnchorRendererPacket> {
        private int x;
        private int y;
        private int z;
        private int charge;

        private AnchorRendererPacketBuilder() {}

        public AnchorRendererPacketBuilder x(int x) {
            this.x = x;
            return this;
        }

        public AnchorRendererPacketBuilder y(int y) {
            this.y = y;
            return this;
        }

        public AnchorRendererPacketBuilder z(int z) {
            this.z = z;
            return this;
        }

        public AnchorRendererPacketBuilder charge(int charge) {
            this.charge = charge;
            return this;
        }

        @Override
        public AnchorRendererPacketBuilder receiver(String receiver) {
            super.receiver(receiver);
            return this;
        }

        @Override
        public AnchorRendererPacket build() {
            return new AnchorRendererPacket(x, y, z, charge, receiver);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AnchorRendererPacketBuilder)) return false;
            AnchorRendererPacketBuilder builder = (AnchorRendererPacketBuilder) o;
            return x == builder.x && y == builder.y && z == builder.z && charge == builder.charge && Objects.equals(receiver, builder.receiver);
        }
    }
}