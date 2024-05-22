package info.preva1l.resentclientapi.mods;

import info.preva1l.resentclientapi.Actor;
import info.preva1l.resentclientapi.ResentMod;

public abstract class OffHand extends ResentMod {

    protected OffHand() {
        super();
        dataRegistry.put("equipped", "{\"action\":\"equip_item\",\"item_id\":\"%item_id%\",\"enchanted\":%enchanted%}".getBytes());
        dataRegistry.put("unequipped", "{\"action\":\"un-equip_item\",\"item_id\":\"%item_id%\",\"enchanted\":%enchanted%}".getBytes());
    }

    public abstract void sendOffhandEquipEvent(Actor actor, String itemId, boolean enchanted);

    public abstract void sendOffhandUnEquipEvent(Actor actor, String itemId, boolean enchanted);

    @Override
    public String getChannel() {
        return "resent:offhand";
    }
}
