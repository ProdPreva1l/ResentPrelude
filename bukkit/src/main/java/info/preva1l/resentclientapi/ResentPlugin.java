package info.preva1l.resentclientapi;

import info.preva1l.resentclientapi.mods.BukkitOffHand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public final class ResentPlugin extends JavaPlugin {
    private static ResentPlugin instance;

    @Override
    public void onEnable() {
        new BukkitResentAPI();
        new BukkitOffHand();

        if (!getConfig().getBoolean("purely-api")) {
            getServer().getPluginManager().registerEvents(new BaseImplementation(this), this);
        }

        getLogger().info("Started BukkitResentAPI");
    }

    @Override
    public void onDisable() {
        getLogger().info("Stopped BukkitResentAPI");
    }

    public void debug(String message) {
        if (getConfig().getBoolean("debug")) {
            getLogger().info("[DEBUG] " + message);
        }
    }

    public ConfigurationSection getModConfig() {
        return getConfig().getConfigurationSection("mods");
    }

    public static ResentPlugin getInstance() {
        return instance;
    }
}
