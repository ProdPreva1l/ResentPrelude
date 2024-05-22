package info.preva1l.resentclientapi;

import info.preva1l.resentclientapi.mods.BukkitOffHand;
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
        Runnable runnable = () -> {
            Optional<BukkitOffHand> mod = ResentAPI.getInstance().getMod(BukkitOffHand.class);
            if (mod.isEmpty() || !mod.get().isAllowed() || !mod.get().isOfficiallyHooked()) {
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
                    mod.get().sendOffhandUnEquipEvent(BukkitAdapter.adaptPlayer(plugin, player),
                            Material.AIR.name(), false);
                } else {
                    mod.get().sendOffhandEquipEvent(BukkitAdapter.adaptPlayer(plugin, player),
                            currentOffhand.getType().name(), !currentOffhand.getEnchantments().isEmpty());
                }
            }
        };

        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, 1L, 10L);
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
}
