package prelude.api;

import java.util.UUID;

@SuppressWarnings("unused")
public abstract class PreludePlayer {
    private final String username;
    private final UUID uuid;

    public PreludePlayer(String username, UUID uuid) {
        this.username = username;
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public abstract void sendPacket(String modid, String msg);
}
