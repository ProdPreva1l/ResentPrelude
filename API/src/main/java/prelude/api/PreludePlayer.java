package prelude.api;

import prelude.network.OutboundPacket;
import prelude.network.processedresults.PreludePlayerInfo;

import java.util.UUID;

@SuppressWarnings("unused")
public abstract class PreludePlayer {
    private final String username;
    private final UUID uuid;

    private final PreludePlayerInfo preludePlayerInfo;

    public PreludePlayer(String username, UUID uuid, PreludePlayerInfo preludePlayerInfo) {
        this.username = username;
        this.uuid = uuid;
        this.preludePlayerInfo = preludePlayerInfo;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public PreludePlayerInfo getPlayerInfo() {
        return preludePlayerInfo;
    }

    /**
     * Sends the packet
     * @param packet Outbound Packet to send
     * @author cire3
     * @since 1.0.0
     */
    public abstract void sendPacket(OutboundPacket packet);
}
