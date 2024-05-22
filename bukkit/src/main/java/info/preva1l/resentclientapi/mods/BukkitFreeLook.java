package info.preva1l.resentclientapi.mods;

import info.preva1l.resentclientapi.ResentAPI;
import info.preva1l.resentclientapi.ResentPlugin;

public final class BukkitFreeLook extends FreeLook {

    public BukkitFreeLook() {
        super();
        ResentAPI.getInstance().addMod(this);

        enabled = true;
    }

    @Override
    public boolean isAllowed() {
        return ResentPlugin.getInstance().getModConfig().getBoolean("free-look.allowed", true);
    }

    @Override
    public boolean isOfficiallyHooked() {
        return true;
    }
}
