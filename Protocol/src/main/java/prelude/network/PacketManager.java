package prelude.network;

import prelude.network.packets.serverbound.HandshakePacket;
import prelude.network.packets.clientbound.*;
import prelude.network.processedresults.PreludePlayerInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PacketManager {
    protected static Set<ServerBoundPacket> serverBoundPackets = new HashSet<>();
    protected static Map<Class<? extends ClientBoundPacket>, ClientBoundPacketBuilder>
            clientBoundPacketClassToBuilderMap = new HashMap<>();



    public abstract ProcessedResult processHandshakeInfo(PreludePlayerInfo info);

    public static ServerBoundPacket getInboundPacketFromString(String string) {
        for (ServerBoundPacket packet : serverBoundPackets)
                if (packet.getPattern().matcher(string.trim().toLowerCase()).matches())
                    return packet.createNewInstanceWithData(string);

        return null;
    }

    /**
     * Gets the appropriate packet builder for a packet
     * @param clazz Class of the packet to create
     * @return The packet builder of that class
     * @throws IllegalArgumentException if the packet class is not registered
     */
    public static <E extends ClientBoundPacket> ClientBoundPacketBuilder getOutboundPacketBuilder(Class<E> clazz) {
        if (clientBoundPacketClassToBuilderMap.containsKey(clazz))
            return clientBoundPacketClassToBuilderMap.get(clazz);

        throw new IllegalArgumentException("Failed to register outbound packet builder, {}!"
                .replace("{}", clazz.getSimpleName()));
    }

    public static void initPackets() {
        // INBOUND
        new HandshakePacket();

        // OUTBOUND
        new WaypointsPacket();
        new ModInitPacket();
        new ModDisablePacket();
        new AnchorRendererPacket();
        new OffhandPacket();
        new ServerTpsPacket();
        new TotemPoppedPacket();
    }
}
