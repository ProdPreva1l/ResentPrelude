package prelude.api.mods;

import prelude.api.Actor;
import prelude.api.ResentMod;

import java.util.Arrays;

public abstract class AnchorRenderer extends ResentMod {
    protected AnchorRenderer() {
        super();
        dataRegistry.put("update", "{\"x\":\"%x%\",\"y\":\"%y%\",\"z\":\"%z%\",\"charge\":\"%charge%\"}".getBytes());
    }

    public void sendPlacedAnchorPacket(Actor actor, int x, int y, int z) {
        sendInteractedAnchorPacket(actor, x, y, z, 0);
    }

    /**
     * @param charge 1 to 3, describing the amount of glowstone in the anchor
     */
    public void sendInteractedAnchorPacket(Actor actor, int x, int y, int z, int charge) {
        actor.sendPacket(this.getChannel(),
                Arrays.toString(this.getData("update"))
                        .replace("%x%", x + "")
                        .replace("%y%", y + "")
                        .replace("%z%", z + "")
                        .replace("%charge%", charge + "").getBytes());
    }

    public void sendBlownUpAnchorPacket(Actor actor, int x, int y, int z) {
        sendInteractedAnchorPacket(actor, x, y, z, -1);
    }

    @Override
    public final String getChannel() {
        return "PRE|AnR";
    }
}
