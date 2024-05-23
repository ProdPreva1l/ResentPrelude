package prelude.mods;

import prelude.PreludePlugin;
import prelude.api.Actor;
import prelude.api.Prelude;
import prelude.api.mods.AnchorRenderer;

public class BukkitAnchorRenderer extends AnchorRenderer {
    public BukkitAnchorRenderer() {
        super();
        Prelude.getInstance().addMod(this);

        enabled = true;
    }

    @Override
    public boolean isAllowed() {
        return PreludePlugin.getInstance().getModConfig().getBoolean("anchor-renderer.allowed", true);
    }

    @Override
    public boolean isOfficiallyHooked() {
        return true;
    }

    @Override
    public void sendPlacedAnchorPacket(Actor actor, int x, int y, int z) {
        super.sendPlacedAnchorPacket(actor, x, y, z);
        PreludePlugin.getInstance().debug("Dispatched PlacedAnchorEvent to " + actor);
    }

    @Override
    public void sendInteractedAnchorPacket(Actor actor, int x, int y, int z, int charge) {
        super.sendInteractedAnchorPacket(actor, x, y, z, charge);
        PreludePlugin.getInstance().debug("Dispatched InteractedAnchorEvent to " + actor);
    }
}
