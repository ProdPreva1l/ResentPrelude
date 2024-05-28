package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;

import java.util.Arrays;

public abstract class OffHand extends ResentMod {

    protected OffHand() {
        super();
        dataRegistry.put("equipped", "{\"action\":\"equip_item\",\"item_id\":\"%item_id%\",\"enchanted\":%enchanted%}");
        dataRegistry.put("unequipped", "{\"action\":\"un-equip_item\",\"item_id\":\"%item_id%\",\"enchanted\":%enchanted%}");
    }

    public void sendOffhandEquipEvent(PreludePlayer preludePlayer, String itemId, boolean enchanted) {
        preludePlayer.sendPacket(this.getModId(),
                this.getData("equipped")
                        .replace("%item_id%", itemId)
                        .replace("%enchanted%", enchanted + ""));
    }

    public void sendOffhandUnEquipEvent(PreludePlayer preludePlayer, String itemId, boolean enchanted) {
        preludePlayer.sendPacket(this.getModId(),
                this.getData("unequipped")
                        .replace("%item_id%", itemId)
                        .replace("%enchanted%", enchanted + ""));
    }

    @Override
    public final String getModId() {
        return "off_hand";
    }
}
