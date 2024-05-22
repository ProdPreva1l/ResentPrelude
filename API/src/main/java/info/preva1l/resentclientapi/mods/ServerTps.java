package info.preva1l.resentclientapi.mods;

import info.preva1l.resentclientapi.Actor;
import info.preva1l.resentclientapi.ResentMod;

import java.util.Arrays;

public abstract class ServerTps extends ResentMod {
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
        return "RES|STps";
    }
}
