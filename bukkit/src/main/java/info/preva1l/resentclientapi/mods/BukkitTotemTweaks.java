package info.preva1l.resentclientapi.mods;

import info.preva1l.resentclientapi.Actor;
import info.preva1l.resentclientapi.ResentAPI;
import info.preva1l.resentclientapi.ResentPlugin;

public final class BukkitTotemTweaks extends TotemTweaks {

    public BukkitTotemTweaks() {
        super();
        ResentAPI.getInstance().addMod(this);

        enabled = true;
    }

    @Override
    public boolean isAllowed() {
        return ResentPlugin.getInstance().getModConfig().getBoolean("totem-tweaks.allowed", true);
    }

    @Override
    public boolean isOfficiallyHooked() {
        return true;
    }

    @Override
    public void sendTotemPoppedEvent(Actor actor) {
        super.sendTotemPoppedEvent(actor);
        ResentPlugin.getInstance().debug("Dispatched TotemPoppedEvent to " + actor);
    }
}
