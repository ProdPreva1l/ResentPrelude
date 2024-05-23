package prelude.mods;

import prelude.PreludePlugin;
import prelude.api.Actor;
import prelude.api.Prelude;
import prelude.api.mods.ServerTps;

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
