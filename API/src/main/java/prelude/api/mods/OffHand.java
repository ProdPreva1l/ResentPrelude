package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;
import prelude.protocol.packets.clientbound.OffhandPacket;
import prelude.protocol.packets.clientbound.OffhandPacket.OffhandPacketBuilder;

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
        OffhandPacketBuilder builder = OffhandPacket.builder();

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
