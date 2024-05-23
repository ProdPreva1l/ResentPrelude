package info.preva1l.prelude;

import info.preva1l.prelude.adapter.PlayerAdapter;
import info.preva1l.prelude.api.Actor;
import info.preva1l.prelude.api.Prelude;
import info.preva1l.prelude.api.PreludeMod;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public final class BukkitPrelude extends Prelude {

    private static final Set<PreludeMod> mods = new HashSet<>();

    public BukkitPrelude() {
        setInstance(this);
    }

    @Override
    public Actor getActor(UUID uuid) throws IllegalStateException {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            throw new IllegalStateException("An actor must be online! Attempted UUID: " + uuid.toString());
        }
        return PlayerAdapter.adaptPlayer(PreludePlugin.getInstance(), player);
    }

    @Override
    public void validateConnection(Actor actor) {
        PreludePlugin.getInstance().debug("Validating mods for " + actor);
        for (PreludeMod mod : mods) {
            if (!mod.isEnabled()) {
                PreludePlugin.getInstance().debug(String.format("Mod %s did not get enabled",
                        mod.getClass().getSimpleName()));
                continue;
            }
            if (!mod.isAllowed()) {
                mod.disableMod(actor);
                PreludePlugin.getInstance().debug(String.format("Mod %s is not allowed and was disabled for %s",
                        mod.getClass().getSimpleName(), actor.getUsername()));
                continue;
            }
            if (mod.isOfficiallyHooked()) {
                mod.initMod(actor);
            }
        }
    }

    @Override
    public <T extends PreludeMod> Optional<T> getMod(Class<T> modClass) {
        return mods.stream()
                .filter(mod -> modClass.isAssignableFrom(mod.getClass()))
                .map(modClass::cast)
                .findFirst();
    }

    @Override
    public void addMod(PreludeMod mod) {
        mods.add(mod);
    }
}
