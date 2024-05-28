package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;

import java.util.Arrays;

public abstract class ServerTps extends ResentMod {
    protected ServerTps() {
        super();
        dataRegistry.put("tps_count", "%server-tps%");
    }

    public void sendServerTpsUpdate(PreludePlayer preludePlayer, double currentTps) {
        preludePlayer.sendPacket(this.getModId(),
                this.getData("tps_count").replace("%server-tps%", currentTps + ""));
    }

    @Override
    public final String getModId() {
        return "server_tps";
    }
}
