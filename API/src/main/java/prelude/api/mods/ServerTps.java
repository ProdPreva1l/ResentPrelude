package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;
import prelude.protocol.packets.clientbound.ServerTpsPacket;
import prelude.protocol.packets.clientbound.ServerTpsPacket.ServerTpsPacketBuilder;

public abstract class ServerTps extends ResentMod {
    protected ServerTps() {
        super();
    }

    public void sendServerTpsUpdate(PreludePlayer preludePlayer, double currentTps) {
        ServerTpsPacketBuilder builder = ServerTpsPacket.builder();

        preludePlayer.sendPacket(
                builder
                        .receiver(this.getReceiverId())
                        .tps(currentTps)
                        .build()
        );
    }

    @Override
    public final String getReceiverId() {
        return "server_tps";
    }
}
