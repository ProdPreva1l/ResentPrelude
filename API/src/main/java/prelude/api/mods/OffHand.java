package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;
import prelude.network.PacketManager;
import prelude.network.packets.outbound.OffhandPacket;
import prelude.network.packets.outbound.OffhandPacket.OffhandPacketBuilder;

public abstract class OffHand extends ResentMod {

    protected OffHand() {
        super();
    }

    public void sendOffhandEquipEvent(PreludePlayer preludePlayer, String itemId, boolean enchanted) {
        sendOffhandEvent(preludePlayer, itemId, enchanted, "equip_item");
    }

    public void sendOffhandUnEquipEvent(PreludePlayer preludePlayer, String itemId, boolean enchanted) {
        sendOffhandEvent(preludePlayer, itemId, enchanted, "un-equip_item");
    }

    private void sendOffhandEvent(PreludePlayer preludePlayer, String itemId, boolean enchanted, String action) {
        OffhandPacketBuilder builder = (OffhandPacketBuilder)
                PacketManager.getOutboundPacketBuilder(OffhandPacket.class);

        preludePlayer.sendPacket(
                builder
                        .receiver(this.getReceiverId())
                        .action(action)
                        .itemId(itemId)
                        .enchanted(enchanted)
                        .build()
        );
    }

    @Override
    public final String getReceiverId() {
        return "off_hand";
    }
}
