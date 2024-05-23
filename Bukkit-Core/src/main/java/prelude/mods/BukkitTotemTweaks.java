package prelude.mods;

import prelude.PreludePlugin;
import prelude.api.Actor;
import prelude.api.Prelude;
import prelude.api.mods.TotemTweaks;

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
