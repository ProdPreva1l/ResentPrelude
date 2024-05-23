package info.preva1l.prelude.mods;

import info.preva1l.prelude.PreludePlugin;
import info.preva1l.prelude.api.Prelude;
import info.preva1l.prelude.api.mods.FreeLook;

public final class BukkitFreeLook extends FreeLook {

    public BukkitFreeLook() {
        super();
        Prelude.getInstance().addMod(this);

        enabled = true;
    }

    @Override
    public boolean isAllowed() {
        return PreludePlugin.getInstance().getModConfig().getBoolean("free-look.allowed", true);
    }

    @Override
    public boolean isOfficiallyHooked() {
        return true;
    }
}
