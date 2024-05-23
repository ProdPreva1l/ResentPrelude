package prelude.mods;

import prelude.PreludePlugin;
import prelude.api.Actor;
import prelude.api.Prelude;
import prelude.api.mods.OffHand;

public final class BukkitOffHand extends OffHand {

    public BukkitOffHand() {
        super();
        Prelude.getInstance().addMod(this);

        enabled = true;
    }

    @Override
    public boolean isAllowed() {
        return PreludePlugin.getInstance().getModConfig().getBoolean("off-hand.allowed", true);
    }

    @Override
    public boolean isOfficiallyHooked() {
        return true;
    }

    @Override
    public void sendOffhandEquipEvent(Actor actor, String itemId, boolean enchanted) {
        super.sendOffhandEquipEvent(actor, itemId, enchanted);
        PreludePlugin.getInstance().debug("Dispatched OffhandEquipEvent to " + actor);
    }

    @Override
    public void sendOffhandUnEquipEvent(Actor actor, String itemId, boolean enchanted) {
        super.sendOffhandUnEquipEvent(actor, itemId, enchanted);
        PreludePlugin.getInstance().debug("Dispatched OffhandEquipEvent to " + actor);
    }
}
