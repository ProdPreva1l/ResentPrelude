package info.preva1l.resentclientapi.mods;

import info.preva1l.resentclientapi.Actor;
import info.preva1l.resentclientapi.ResentAPI;
import info.preva1l.resentclientapi.ResentPlugin;

public final class BukkitServerTps extends ServerTps {

    public BukkitServerTps() {
        super();
        ResentAPI.getInstance().addMod(this);
        enabled = true;
    }

    @Override
    public void sendServerTpsUpdate(Actor actor, double currentTps) {
        super.sendServerTpsUpdate(actor, currentTps);
        ResentPlugin.getInstance().debug("Dispatched ServerTpsUpdate to " + actor);
    }

    @Override
    public boolean isAllowed() {
        return ResentPlugin.getInstance().getModConfig().getBoolean("server-tps.allowed", true);
    }

    @Override
    public boolean isOfficiallyHooked() {
        return true;
    }
}
