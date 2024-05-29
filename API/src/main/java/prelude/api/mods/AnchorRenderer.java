package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;

import java.util.Arrays;

public abstract class AnchorRenderer extends ResentMod {
    protected AnchorRenderer() {
        super();
        dataRegistry.put("update", "{\"x\":\"%x%\",\"y\":\"%y%\",\"z\":\"%z%\",\"charge\":\"%charge%\"}");
    }

    public void sendPlacedAnchorPacket(PreludePlayer preludePlayer, int x, int y, int z) {
        sendInteractedAnchorPacket(preludePlayer, x, y, z, 0);
    }

    /**
     * @param charge 1 to 3, describing the amount of glowstone in the anchor
     */
    public void sendInteractedAnchorPacket(PreludePlayer preludePlayer, int x, int y, int z, int charge) {
        preludePlayer.sendPacket(this.getModId(),
                this.getData("update")
                        .replace("%x%", x + "")
                        .replace("%y%", y + "")
                        .replace("%z%", z + "")
                        .replace("%charge%", charge + ""));
    }

    public void sendBlownUpAnchorPacket(PreludePlayer preludePlayer, int x, int y, int z) {
        sendInteractedAnchorPacket(preludePlayer, x, y, z, -1);
    }

    @Override
    public final String getModId() {
        return "anchor_renderer";
    }
}
