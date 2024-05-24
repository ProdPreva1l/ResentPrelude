package prelude.adapter.impl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import prelude.adapter.PlayerAdapter;
import prelude.adapter.VersionAdapter;
import prelude.api.mods.AnchorRenderer;
import prelude.api.mods.OffHand;
import prelude.api.mods.TotemTweaks;

import java.util.HashMap;
import java.util.Map;

public final class Adapter_1_9 implements VersionAdapter {
    private final JavaPlugin plugin;
    private final Map<Player, ItemStack> offhandItemMap = new HashMap<>();

    public Adapter_1_9(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerAnchorListener(AnchorRenderer anchorMod) {
        // Do nothing
    }

    @Override
    public void registerTotemListener(TotemTweaks totemMod) {
        // Do nothing
    }

    @Override
    public Runnable getOffHandRunnable(OffHand offhandMod) {
        // This is my solution for monitoring offhand items
        // Cires was to track the ItemStacks client side, while this makes more sense,
        // I think we should take as much stress off the client as possible
        // if you come up with a better implementation please make a PR
        return () -> {
            if (!offhandMod.isAllowed() || !offhandMod.isOfficiallyHooked()) {
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
                if (currentOffhand.getType() == Material.AIR) {
                    offhandMod.sendOffhandUnEquipEvent(PlayerAdapter.adaptPlayer(plugin, player),
                            Material.AIR.name(), false);
                } else {
                    offhandMod.sendOffhandEquipEvent(PlayerAdapter.adaptPlayer(plugin, player),
                            currentOffhand.getType().name(), !currentOffhand.getEnchantments().isEmpty());
                }
            }
        };
    }

    @Override
    public boolean hasOffHandSupport() {
        return true;
    }
}
