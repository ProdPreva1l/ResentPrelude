package info.preva1l.resentclientapi;

import info.preva1l.resentclientapi.mods.BukkitOffHand;
import info.preva1l.resentclientapi.mods.BukkitServerTps;
import info.preva1l.resentclientapi.mods.BukkitTotemTweaks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The base Implementation of the ResentAPI for bukkit.
 */
public class BaseImplementation implements Listener {
    private final ResentPlugin plugin;

    private final Map<Player, ItemStack> offhandItemMap = new HashMap<>();

    public BaseImplementation(ResentPlugin plugin) {
        this.plugin = plugin;

        // This is my solution for monitoring offhand items
        // Cires was to track the ItemStacks client side, while this makes more sense,
        // I think we should take as much stress off the client as possible
        // if you come up with a better implementation please make a PR
        Runnable offhandRunnable = () -> {
            Optional<BukkitOffHand> offhandMod = ResentAPI.getInstance().getMod(BukkitOffHand.class);
            if (offhandMod.isEmpty() || !offhandMod.get().isAllowed() || !offhandMod.get().isOfficiallyHooked()) {
                return;
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                ItemStack currentOffhand = player.getInventory().getItemInOffHand();
                if (!offhandItemMap.containsKey(player)) {
                    offhandItemMap.put(player, currentOffhand);
                    continue;
                }
                ItemStack cachedOffhand = offhandItemMap.get(player);
                if (cachedOffhand == currentOffhand) {
                    continue;
                }
                offhandItemMap.replace(player, currentOffhand);
                if (currentOffhand.getType().isAir()) {
                    offhandMod.get().sendOffhandUnEquipEvent(BukkitAdapter.adaptPlayer(plugin, player),
                            Material.AIR.name(), false);
                } else {
                    offhandMod.get().sendOffhandEquipEvent(BukkitAdapter.adaptPlayer(plugin, player),
                            currentOffhand.getType().name(), !currentOffhand.getEnchantments().isEmpty());
                }
            }
        };

        Runnable tpsRunnable = () -> {
            Optional<BukkitServerTps> tpsMod = ResentAPI.getInstance().getMod(BukkitServerTps.class);
            if (tpsMod.isEmpty() || !tpsMod.get().isAllowed() || !tpsMod.get().isOfficiallyHooked()) {
                return;
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                tpsMod.get().sendServerTpsUpdate(BukkitAdapter.adaptPlayer(plugin, player), getTPS()[0]);
            }
        };

        ResentAPI.getInstance().getMod(BukkitServerTps.class).ifPresent((tpsMod) -> {
            if (tpsMod.isOfficiallyHooked()) {
                plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, tpsRunnable, 1L, 20L);
            }
        });

        ResentAPI.getInstance().getMod(BukkitOffHand.class).ifPresent((offHandMod) -> {
            if (offHandMod.isOfficiallyHooked()) {
                plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, offhandRunnable, 1L, 10L);
            }
        });
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ResentAPI.getInstance().validateConnection(BukkitAdapter.adaptPlayer(plugin, player));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEntityResurrect(EntityResurrectEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Optional<BukkitTotemTweaks> mod = ResentAPI.getInstance().getMod(BukkitTotemTweaks.class);
            if (mod.isEmpty() || !mod.get().isAllowed() || !mod.get().isOfficiallyHooked()) {
                return;
            }
            mod.get().sendTotemPoppedEvent(BukkitAdapter.adaptPlayer(plugin, player));
        }
    }


    private double[] getTPS() {
        try {
            Object minecraftServer = getMinecraftServer();
            Field tpsField = minecraftServer.getClass().getDeclaredField("recentTps");
            tpsField.setAccessible(true);
            return (double[]) tpsField.get(minecraftServer);
        } catch (Exception e) {
            ResentPlugin.getInstance().debug(e.getMessage());
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
