package prelude;

import prelude.adapter.PlayerAdapter;
import prelude.adapter.VersionAdapter;
import prelude.api.Prelude;
import prelude.mods.BukkitAnchorRenderer;
import prelude.mods.BukkitOffHand;
import prelude.mods.BukkitServerTps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * The base Implementation of the ResentAPI for bukkit.
 */
public class BaseImplementation implements Listener {
    private final PreludePlugin plugin;

    public BaseImplementation(PreludePlugin plugin) {
        this.plugin = plugin;

        Runnable tpsRunnable = () -> {
            Optional<BukkitServerTps> tpsMod = Prelude.getInstance().getMod(BukkitServerTps.class);
            if (tpsMod.isEmpty() || !tpsMod.get().isAllowed() || !tpsMod.get().isOfficiallyHooked()) {
                return;
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                tpsMod.get().sendServerTpsUpdate(PlayerAdapter.adaptPlayer(plugin, player), getTPS()[0]);
            }
        };

        Prelude.getInstance().getMod(BukkitServerTps.class).ifPresent((tpsMod) -> {
            if (tpsMod.isOfficiallyHooked()) {
                plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, tpsRunnable, 1L, 20L);
            }
        });


        // Post 1.8 things (runs on 1.16.5 api)
        plugin.getAdapter().ifPresent(VersionAdapter::registerEvents);

        Prelude.getInstance().getMod(BukkitOffHand.class).ifPresent((offHandMod) ->
            plugin.getAdapter().ifPresent((adapter) -> {
                if (!offHandMod.isOfficiallyHooked()) {
                    return;
                }
                plugin.getServer().getScheduler()
                        .runTaskTimerAsynchronously(plugin, adapter.getOffHandRunnable(), 1L, 10L);
            })
        );
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Prelude.getInstance().validateConnection(PlayerAdapter.adaptPlayer(plugin, player));
    }


    private double[] getTPS() {
        try {
            Object minecraftServer = getMinecraftServer();
            Field tpsField = minecraftServer.getClass().getDeclaredField("recentTps");
            tpsField.setAccessible(true);
            return (double[]) tpsField.get(minecraftServer);
        } catch (Exception e) {
            PreludePlugin.getInstance().debug(e.getMessage());
            return new double[]{-1, -1, -1};
        }
    }

    private Object getMinecraftServer() throws Exception {
        Object craftServer = Bukkit.getServer();
        Field consoleField = craftServer.getClass().getDeclaredField("console");
        consoleField.setAccessible(true);
        return consoleField.get(craftServer);
    }
}
