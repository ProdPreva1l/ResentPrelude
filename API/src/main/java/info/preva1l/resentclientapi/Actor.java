package info.preva1l.resentclientapi;

import java.util.UUID;

public abstract class Actor {
    private final String username;
    private final UUID uuid;

    public Actor(String username, UUID uuid) {
        this.username = username;
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public abstract void sendPacket(String channel, byte[] data);
}
