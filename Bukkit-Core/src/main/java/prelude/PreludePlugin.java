package prelude;

import prelude.adapter.VersionAdapter;
import prelude.mods.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public final class PreludePlugin extends JavaPlugin {
    private static PreludePlugin instance;
    private VersionAdapter adapter = null;

    @Override
    public void onLoad() {
        String version = Bukkit.getBukkitVersion().substring(0, 5);
        getLogger().info(version);
        if (version.startsWith("1.16")
                || version.startsWith("1.17")
                || version.startsWith("1.18")
                || version.startsWith("1.19")
                || version.startsWith("1.20")
                || version.startsWith("1.21")) {
            adapter = new Adapter_1_16_5_And_Later(this);
        } else {
            getLogger().warning("Server is running below 1.16.5 and does not support all features!");
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        new BukkitPrelude();

        if (getConfig().getBoolean("purely-api")) {
            getLogger().info("Started Resent Client's Prelude API");
            return;
        }

        new BukkitOffHand();
        new BukkitTotemTweaks();
        new BukkitFreeLook();
        new BukkitServerTps();
        new BukkitAnchorRenderer();

        getServer().getPluginManager().registerEvents(new BaseImplementation(this), this);

        getLogger().info("Started Resent Client's Prelude API");
    }

    @Override
    public void onDisable() {
        getLogger().info("Stopped Prelude API");
    }

    public void debug(String message) {
        if (getConfig().getBoolean("debug")) {
            getLogger().info("[DEBUG] " + message);
        }
    }

    public ConfigurationSection getModConfig() {
        return getConfig().getConfigurationSection("mods");
    }

    public static PreludePlugin getInstance() {
        return instance;
    }

    public Optional<VersionAdapter> getAdapter() {
        return Optional.ofNullable(adapter);
    }
}