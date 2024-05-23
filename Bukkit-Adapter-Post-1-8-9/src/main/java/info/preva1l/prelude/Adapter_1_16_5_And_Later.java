package info.preva1l.prelude;

import info.preva1l.prelude.adapter.PlayerAdapter;
import info.preva1l.prelude.adapter.VersionAdapter;
import info.preva1l.prelude.api.Prelude;
import info.preva1l.prelude.api.mods.OffHand;
import info.preva1l.prelude.api.mods.TotemTweaks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public final class Adapter_1_16_5_And_Later implements VersionAdapter, Listener {
    private final JavaPlugin plugin;
    private final Map<Player, ItemStack> offhandItemMap = new HashMap<>();

    public Adapter_1_16_5_And_Later(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onResurrectEvent(EntityResurrectEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Optional<TotemTweaks> mod = Prelude.getInstance().getMod(TotemTweaks.class);
            if (mod.isEmpty() || !mod.get().isAllowed() || !mod.get().isOfficiallyHooked()) {
                return;
            }
            mod.get().sendTotemPoppedEvent(PlayerAdapter.adaptPlayer(plugin, player));
        }
    }

    @Override
    public void registerResurrectEvent() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public Runnable getOffHandRunnable() {

        // This is my solution for monitoring offhand items
        // Cires was to track the ItemStacks client side, while this makes more sense,
        // I think we should take as much stress off the client as possible
        // if you come up with a better implementation please make a PR
        return () -> {
            Optional<OffHand> offhandMod = Prelude.getInstance().getMod(OffHand.class);
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
                    offhandMod.get().sendOffhandUnEquipEvent(PlayerAdapter.adaptPlayer(plugin, player),
                            Material.AIR.name(), false);
                } else {
                    offhandMod.get().sendOffhandEquipEvent(PlayerAdapter.adaptPlayer(plugin, player),
                            currentOffhand.getType().name(), !currentOffhand.getEnchantments().isEmpty());
                }
            }
        };
    }
}
