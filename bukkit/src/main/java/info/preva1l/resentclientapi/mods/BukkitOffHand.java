package info.preva1l.resentclientapi.mods;

import info.preva1l.resentclientapi.Actor;
import info.preva1l.resentclientapi.ResentAPI;
import info.preva1l.resentclientapi.ResentPlugin;

public final class BukkitOffHand extends OffHand {

    public BukkitOffHand() {
        super();
        ResentAPI.getInstance().addMod(this);

        enabled = true;
    }

    @Override
    public boolean isAllowed() {
        return ResentPlugin.getInstance().getModConfig().getBoolean("off-hand.allowed", true);
    }

    @Override
    public boolean isOfficiallyHooked() {
        return true;
    }

    @Override
    public void sendOffhandEquipEvent(Actor actor, String itemId, boolean enchanted) {
        super.sendOffhandEquipEvent(actor, itemId, enchanted);
        ResentPlugin.getInstance().debug("Dispatched OffhandEquipEvent to " + actor);
    }

    @Override
    public void sendOffhandUnEquipEvent(Actor actor, String itemId, boolean enchanted) {
        super.sendOffhandUnEquipEvent(actor, itemId, enchanted);
        ResentPlugin.getInstance().debug("Dispatched OffhandEquipEvent to " + actor);
    }
}
