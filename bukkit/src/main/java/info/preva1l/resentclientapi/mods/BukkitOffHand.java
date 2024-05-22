package info.preva1l.resentclientapi.mods;

import info.preva1l.resentclientapi.Actor;
import info.preva1l.resentclientapi.ResentAPI;
import info.preva1l.resentclientapi.ResentPlugin;

import java.util.Arrays;

public final class BukkitOffHand extends OffHand {

    public BukkitOffHand() {
        super();
        ResentAPI.getInstance().addMod(this);

        enabled = true;
    }

    @Override
    public boolean isAllowed() {
        return ResentPlugin.getInstance().getModConfig().getBoolean("off-hand.allowed", true);
    }

    @Override
    public boolean isOfficiallyHooked() {
        return true;
    }

    @Override
    public void sendOffhandEquipEvent(Actor actor, String itemId, boolean enchanted) {
        actor.sendPacket(this.getChannel(),
                Arrays.toString(this.getData("equipped"))
                        .replace("%item_id%", itemId)
                        .replace("%enchanted%", enchanted + "").getBytes());
    }

    @Override
    public void sendOffhandUnEquipEvent(Actor actor, String itemId, boolean enchanted) {
        actor.sendPacket(this.getChannel(),
                Arrays.toString(this.getData("unequipped"))
                        .replace("%item_id%", itemId)
                        .replace("%enchanted%", enchanted + "").getBytes());
    }
}
