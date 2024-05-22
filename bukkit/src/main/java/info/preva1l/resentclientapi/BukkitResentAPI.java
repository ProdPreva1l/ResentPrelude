package info.preva1l.resentclientapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public final class BukkitResentAPI extends ResentAPI {

    private static final Set<ResentMod> mods = new HashSet<>();

    public BukkitResentAPI() {
        setInstance(this);
    }

    @Override
    public Actor getActor(UUID uuid) throws IllegalStateException {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            throw new IllegalStateException("An actor must be online! Attempted UUID: " + uuid.toString());
        }
        return BukkitAdapter.adaptPlayer(ResentPlugin.getInstance(), player);
    }

    @Override
    public void validateConnection(Actor actor) {
        ResentPlugin.getInstance().debug("Validating mods for " + actor);
        for (ResentMod mod : mods) {
            if (!mod.isEnabled()) {
                ResentPlugin.getInstance().debug(String.format("Mod %s did not get enabled",
                        mod.getClass().getSimpleName()));
                continue;
            }
            if (!mod.isAllowed()) {
                mod.disableMod(actor);
                ResentPlugin.getInstance().debug(String.format("Mod %s is not allowed and was disabled for %s",
                        mod.getClass().getSimpleName(), actor.getUsername()));
                continue;
            }
            if (mod.isOfficiallyHooked()) {
                mod.initMod(actor);
            }
        }
    }

    @Override
    public <T extends ResentMod> Optional<T> getMod(Class<T> modClass) {
        return mods.stream()
                .filter(mod -> modClass.isAssignableFrom(mod.getClass()))
                .map(modClass::cast)
                .findFirst();
    }

    @Override
    public void addMod(ResentMod mod) {
        mods.add(mod);
    }
}
