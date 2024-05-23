package prelude.mods;

import prelude.PreludePlugin;
import prelude.api.Prelude;
import prelude.api.mods.FreeLook;

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
