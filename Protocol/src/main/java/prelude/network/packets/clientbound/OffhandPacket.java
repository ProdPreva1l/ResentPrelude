package prelude.network.packets.clientbound;

import prelude.network.ClientBoundPacket;
import prelude.network.ClientBoundPacketBuilder;

import java.util.Objects;

public class OffhandPacket extends ClientBoundPacket {
    public static final String OFFHAND_PACKET_MESSAGE_FORMAT =
            "{" +
                    "\"action\":\"equip_item\"," +
                    "\"item_id\":\"%item_id%\"," +
                    "\"enchanted\":\"%enchanted%\"" +
            "}";

    private String action;
    private String itemId;
    private boolean enchanted;

    public OffhandPacket() {
        super(OffhandPacket.class);
    }

    private OffhandPacket(String receiver, String action, String itemId, boolean enchanted) {
        this.action = action;
        this.itemId = itemId;
        this.enchanted = enchanted;
        this.receiver = receiver;
    }

    @Override
    public String serialize() {
        return GENERIC_PACKET_FORMAT
                .replace("%receiver%", receiver)
                .replace("%message%", OFFHAND_PACKET_MESSAGE_FORMAT
                        .replace("%action%", action)
                        .replace("%item_id%", itemId)
                        .replace("%enchanted%", String.valueOf(enchanted))
                );
    }

    @Override
    public ClientBoundPacketBuilder<? extends ClientBoundPacket> builder() {
        return new OffhandPacketBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OffhandPacket)) return false;
        OffhandPacket that = (OffhandPacket) o;
        return enchanted == that.enchanted && Objects.equals(action, that.action) && Objects.equals(itemId, that.itemId)
                && Objects.equals(receiver, that.receiver);
    }

    public static class OffhandPacketBuilder extends ClientBoundPacketBuilder<OffhandPacket> {
        private String action;
        private String itemId;
        private boolean enchanted;

        private OffhandPacketBuilder() {}

        public OffhandPacketBuilder action(String action) {
            this.action = action;
            return this;
        }

        public OffhandPacketBuilder itemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public OffhandPacketBuilder enchanted(boolean enchanted) {
            this.enchanted = enchanted;
            return this;
        }

        @Override
        public OffhandPacketBuilder receiver(String receiver) {
            super.receiver(receiver);
            return this;
        }

        @Override
        public OffhandPacket build() {
            return new OffhandPacket(receiver, action, itemId, enchanted);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof OffhandPacketBuilder)) return false;
            OffhandPacketBuilder builder = (OffhandPacketBuilder) o;
            return enchanted == builder.enchanted && Objects.equals(action, builder.action) &&
                    Objects.equals(itemId, builder.itemId) && Objects.equals(receiver, builder.receiver);
        }
    }
}
