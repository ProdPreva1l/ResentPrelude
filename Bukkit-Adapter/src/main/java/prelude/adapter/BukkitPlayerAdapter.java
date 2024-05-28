package prelude.adapter;

import prelude.api.Prelude;
import prelude.api.PreludePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class BukkitPlayerAdapter {
    private static final Map<Player, PreludePlayer> map = new HashMap<>();

    public static PreludePlayer adaptPlayer(JavaPlugin plugin, Player player) {
        if (map.containsKey(player))
            return map.get(player);

        PreludePlayer preludePlayer = new PreludePlayer(player.getName(), player.getUniqueId()) {
            @Override
            public void sendPacket(String modId, String message) {
                player.sendPluginMessage(plugin, Prelude.CHANNEL, (modId + ";" + message).getBytes());
            }
        };

        map.put(player, preludePlayer);

        return preludePlayer;
    }

    public static void remove(Player player) {
        map.remove(player);
    }
}