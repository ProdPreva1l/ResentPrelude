package prelude.mods;

import prelude.PreludePlugin;
import prelude.api.PreludePlayer;
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
    public void sendOffhandEquipEvent(PreludePlayer preludePlayer, String itemId, boolean enchanted) {
        super.sendOffhandEquipEvent(preludePlayer, itemId, enchanted);
        PreludePlugin.getInstance().debug("Dispatched OffhandEquipEvent to " + preludePlayer);
    }

    @Override
    public void sendOffhandUnEquipEvent(PreludePlayer preludePlayer, String itemId, boolean enchanted) {
        super.sendOffhandUnEquipEvent(preludePlayer, itemId, enchanted);
        PreludePlugin.getInstance().debug("Dispatched OffhandEquipEvent to " + preludePlayer);
    }
}
