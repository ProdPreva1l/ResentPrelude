package prelude.network;

import prelude.network.packets.inbound.HandshakePacket;
import prelude.network.packets.outbound.*;
import prelude.network.processedresults.PreludePlayerInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PacketManager {
    protected static Set<InboundPacket> inboundPackets = new HashSet<>();
    protected static Map<Class<? extends OutboundPacket>, OutboundPacketBuilder>
            outboundPackets = new HashMap<>();



    public abstract ProcessedResult processHandshakeInfo(PreludePlayerInfo info);

    public static InboundPacket getInboundPacketFromString(String string) {
        for (InboundPacket packet : inboundPackets)
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
    public static <E extends OutboundPacket> OutboundPacketBuilder getOutboundPacketBuilder(Class<E> clazz) {
        if (outboundPackets.containsKey(clazz))
            return outboundPackets.get(clazz);

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
