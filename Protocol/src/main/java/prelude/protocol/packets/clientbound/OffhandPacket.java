package prelude.protocol.packets.clientbound;

import lombok.Builder;
import prelude.protocol.ClientBoundPacket;

import java.util.Objects;

@Builder
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
    private String receiver;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OffhandPacket)) return false;
        OffhandPacket that = (OffhandPacket) o;
        return enchanted == that.enchanted && Objects.equals(action, that.action) && Objects.equals(itemId, that.itemId)
                && Objects.equals(receiver, that.receiver);
    }
}
