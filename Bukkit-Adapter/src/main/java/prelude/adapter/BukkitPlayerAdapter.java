package prelude.adapter;

import prelude.api.Actor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class BukkitPlayerAdapter {
    private static final Map<Player, Actor> map = new HashMap<>();

    public static Actor adaptPlayer(JavaPlugin plugin, Player player) {
        if (map.containsKey(player))
            return map.get(player);

        Actor actor = new Actor(player.getName(), player.getUniqueId()) {
            @Override
            public void sendPacket(String channel, byte[] data) {
                player.sendPluginMessage(plugin, channel, data);
            }
        };

        map.put(player, actor);

        return actor;
    }

    public static void remove(Player player) {
        map.remove(player);
    }
}