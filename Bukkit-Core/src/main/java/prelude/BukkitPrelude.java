package prelude;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import prelude.adapter.PlayerAdapter;
import prelude.api.Actor;
import prelude.api.Prelude;
import prelude.api.ResentMod;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public final class BukkitPrelude extends Prelude {

    private static final Set<ResentMod> mods = new HashSet<>();

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
        for (ResentMod mod : mods) {
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
    public <T extends ResentMod> Optional<T> getMod(Class<T> modClass) {
        Preconditions.checkArgument(Modifier.isFinal(modClass.getModifiers()));
        Preconditions.checkArgument(!Modifier.isAbstract(modClass.getModifiers()));

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
