package info.preva1l.prelude.mods;

import info.preva1l.prelude.PreludePlugin;
import info.preva1l.prelude.api.Actor;
import info.preva1l.prelude.api.Prelude;
import info.preva1l.prelude.api.mods.ServerTps;

public final class BukkitServerTps extends ServerTps {

    public BukkitServerTps() {
        super();
        Prelude.getInstance().addMod(this);
        enabled = true;
    }

    @Override
    public void sendServerTpsUpdate(Actor actor, double currentTps) {
        super.sendServerTpsUpdate(actor, currentTps);
        PreludePlugin.getInstance().debug("Dispatched ServerTpsUpdate to " + actor);
    }

    @Override
    public boolean isAllowed() {
        return PreludePlugin.getInstance().getModConfig().getBoolean("server-tps.allowed", true);
    }

    @Override
    public boolean isOfficiallyHooked() {
        return true;
    }
}
