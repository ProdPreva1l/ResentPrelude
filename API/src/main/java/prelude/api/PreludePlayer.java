package prelude.api;

import prelude.api.packet.processedresults.PreludePlayerInfo;

import java.util.UUID;

@SuppressWarnings("unused")
public abstract class PreludePlayer {
    public static final String PACKET_FORMAT = "{\"modid\": \"%modid%\", \"message\": \"%message%\"}";

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
     * Sends a packet with JSON formatting {@code {"modid": %modid%, "message": %message%}}
     * @param modid Mod ID
     * @param msg The message to send. Can be formatted in JSON
     * @author cire3
     * @since 1.0.0
     */
    public abstract void sendPacket(String modid, String msg);
}
