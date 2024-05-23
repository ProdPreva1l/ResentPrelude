package info.preva1l.prelude.api.mods;

import info.preva1l.prelude.api.Actor;
import info.preva1l.prelude.api.PreludeMod;

import java.util.Arrays;

public abstract class ServerTps extends PreludeMod {
    protected ServerTps() {
        super();
        dataRegistry.put("tps_count", "%server-tps%".getBytes());
    }

    public void sendServerTpsUpdate(Actor actor, double currentTps) {
        actor.sendPacket(this.getChannel(),
                Arrays.toString(this.getData("tps_count"))
                        .replace("%server-tps%", currentTps + "").getBytes());
    }

    @Override
    public String getChannel() {
        return "PRE|STps";
    }
}
