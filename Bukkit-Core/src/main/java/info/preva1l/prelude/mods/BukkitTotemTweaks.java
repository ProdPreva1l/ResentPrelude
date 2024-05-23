package info.preva1l.prelude.mods;

import info.preva1l.prelude.PreludePlugin;
import info.preva1l.prelude.api.Actor;
import info.preva1l.prelude.api.Prelude;
import info.preva1l.prelude.api.mods.TotemTweaks;

public final class BukkitTotemTweaks extends TotemTweaks {

    public BukkitTotemTweaks() {
        super();
        Prelude.getInstance().addMod(this);

        enabled = true;
    }

    @Override
    public boolean isAllowed() {
        return PreludePlugin.getInstance().getModConfig().getBoolean("totem-tweaks.allowed", true);
    }

    @Override
    public boolean isOfficiallyHooked() {
        return true;
    }

    @Override
    public void sendTotemPoppedEvent(Actor actor) {
        super.sendTotemPoppedEvent(actor);
        PreludePlugin.getInstance().debug("Dispatched TotemPoppedEvent to " + actor);
    }
}
