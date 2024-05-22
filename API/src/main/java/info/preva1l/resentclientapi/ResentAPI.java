package info.preva1l.resentclientapi;


import java.util.Optional;
import java.util.UUID;

public abstract class ResentAPI {
    /**
     * The API instance.
     */
    private static ResentAPI instance = null;

    /**
     * Get an actor from a UUID, the player who owns the UUID must be online
     * @param uuid uuid of the actor to get
     * @return an Actor object
     * @throws IllegalStateException if the player is not online
     */
    public abstract Actor getActor(UUID uuid) throws IllegalStateException;

    /**
     * Run checks on all mods to either send the disable or the init packet to the client
     * @param actor player to validate
     */
    public abstract void validateConnection(Actor actor);

    /**
     * Get a ResentMod instance.
     * @param modClass the mod to get
     * @return the instance of the mod
     */
    public abstract <T extends ResentMod> Optional<T> getMod(Class<T> modClass);

    /**
     * Register a ResentMod hook.
     * @param mod the mod instance to register
     * @apiNote Internal use only
     */
    public abstract void addMod(ResentMod mod);

    /**
     * Get the API instance.
     * @return the instance of the api
     */
    public static ResentAPI getInstance() {
        return instance;
    }

    /**
     * Set the instance.
     * @throws IllegalStateException if the instance is already assigned
     */
    public static void setInstance(ResentAPI newInstance) {
        if (instance != null) {
            throw new IllegalStateException("Instance has already been set");
        }
        instance = newInstance;
    }
}