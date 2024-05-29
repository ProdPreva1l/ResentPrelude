package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;

public abstract class ServerTps extends ResentMod {
    protected ServerTps() {
        super();
        dataRegistry.put("tps_count", "%server-tps%");
    }

    public void sendServerTpsUpdate(PreludePlayer preludePlayer, double currentTps) {
        preludePlayer.sendPacket(this.getReceiverId(),
                this.getData("tps_count").replace("%server-tps%", currentTps + ""));
    }

    @Override
    public final String getReceiverId() {
        return "server_tps";
    }
}
