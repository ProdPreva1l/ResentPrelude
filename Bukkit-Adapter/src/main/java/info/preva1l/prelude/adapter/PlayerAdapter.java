package info.preva1l.prelude.adapter;

import info.preva1l.prelude.api.Actor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerAdapter {
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