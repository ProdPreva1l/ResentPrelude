package info.preva1l.resentclientapi;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class BukkitAdapter {
    public static Actor adaptPlayer(JavaPlugin plugin, Player player) {
        return new Actor(player.getName(), player.getUniqueId()) {
            @Override
            public void sendPacket(String channel, byte[] data) {
                player.sendPluginMessage(
                        plugin,
                        channel,
                        data);
            }
        };
    }
}