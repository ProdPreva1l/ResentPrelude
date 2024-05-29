package prelude.api;


import prelude.api.packet.PacketManager;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("unused")
public abstract class Prelude {
    public static final String CHANNEL = "resent:prelude";

    /**
     * The API instance.
     */
    private static Prelude instance = null;

    /**
     * The PacketManager instance.
     */
    private static PacketManager packetManager = null;

    /**
     * Get an actor from a UUID, the player who owns the UUID must be online
     * @param uuid uuid of the actor to get
     * @return an Actor object
     * @throws IllegalStateException if the player is not online
     */
    public abstract PreludePlayer getActor(UUID uuid) throws IllegalStateException;

    /**
     * Run checks on all mods to either send the disable or the init packet to the client
     * @param preludePlayer player to validate
     */
    public abstract void validateConnection(PreludePlayer preludePlayer);

    /**
     * Get a ResentMod instance.
     * @param modClass the mod to get
     * @return the instance of the mod
     * @throws IllegalArgumentException if the modClass is not final or is abstract
     */
    public abstract <T extends ResentMod> Optional<T> getMod(Class<T> modClass) throws IllegalArgumentException;

    /**
     * Register a ResentMod hook.
     * @param mod the mod instance to register
     * @apiNote Internal use only
     */
    public abstract void addMod(ResentMod mod);

    /**
     * Get a set of all registered mods.
     * @return immutable set of all registered mods
     * @since 2.0.1
     */
    public abstract Set<ResentMod> getMods();

    /**
     * Get the API instance.
     * @return the instance of the api
     */
    public static Prelude getInstance() {
        return instance;
    }

    /**
     * Get the PacketManager instance.
     * @return the instance of the packet manager
     */
    public static PacketManager getPacketManager() {
        return packetManager;
    }

    /**
     * Set the instance.
     * @throws IllegalStateException if the instance is already assigned
     */
    public static void setInstance(Prelude newInstance) {
        if (instance != null) {
            throw new IllegalStateException("Instance has already been set");
        }
        instance = newInstance;
    }

    /**
     * Set the instance of the packet manager.
     * @throws IllegalStateException if the packet maanager instance is already assigned
     */
    public static void setPacketManager(PacketManager newPacketManager) {
        if (packetManager != null) {
            throw new IllegalStateException("Packet Manager instance has already been set");
        }
        packetManager = newPacketManager;
    }
}