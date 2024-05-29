package prelude.api;

import prelude.api.packet.PacketManager;
import prelude.api.packet.packets.outbound.ModDisablePacket;
import prelude.api.packet.packets.outbound.ModInitPacket;

import java.util.HashMap;
import java.util.Map;

public abstract class ResentMod {
    protected boolean enabled = false;

    protected Map<String, String> dataRegistry = new HashMap<>();

    protected ResentMod() {
        dataRegistry.put("empty", new String(new byte[0]));
        dataRegistry.put("init", "init");
        dataRegistry.put("disable", "disable");
    }

    public void initMod(PreludePlayer preludePlayer) {
        ModInitPacket.ModInitPacketBuilder builder = (ModInitPacket.ModInitPacketBuilder)
                PacketManager.getOutboundPacketBuilder(ModInitPacket.class);

        if (builder == null)
            throw new RuntimeException("Failed to register outbound packet builder, {}!"
                    .replace("{}", ModInitPacket.class.getSimpleName()));

        builder.setReceiver(this.getReceiverId());

        preludePlayer.sendPacket(builder.build());
    }

    public void disableMod(PreludePlayer preludePlayer) {
        ModDisablePacket.ModDisablePacketBuilder builder = (ModDisablePacket.ModDisablePacketBuilder)
                PacketManager.getOutboundPacketBuilder(ModDisablePacket.class);

        if (builder == null)
            throw new RuntimeException("Failed to register outbound packet builder, {}!"
                    .replace("{}", ModDisablePacket.class.getSimpleName()));
        builder.setReceiver(this.getReceiverId());

        preludePlayer.sendPacket(builder.build());
    }

    public abstract String getReceiverId();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public abstract boolean isAllowed();

    public boolean isOfficiallyHooked() {
        return false;
    }

    public String getData(String key) {
        String data = dataRegistry.get(key);
        if (data == null) {
            throw new IllegalArgumentException("Data key " + key + " not found in registry!");
        }
        return data;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
