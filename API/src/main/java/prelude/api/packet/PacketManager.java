package prelude.api.packet;

import prelude.api.packet.packets.inbound.HandshakePacket;
import prelude.api.packet.packets.outbound.ModDisablePacket;
import prelude.api.packet.packets.outbound.ModInitPacket;
import prelude.api.packet.packets.outbound.WaypointsPacket;
import prelude.api.packet.processedresults.PreludePlayerInfo;

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

    public static <E extends OutboundPacket> OutboundPacketBuilder getOutboundPacketBuilder(Class<E> clazz) {
        if (outboundPackets.containsKey(clazz))
            return outboundPackets.get(clazz);

        return null;
    }

    public static void initPackets() {
        // INBOUND
        new HandshakePacket();

        // OUTBOUND
        new WaypointsPacket();
        new ModInitPacket();
        new ModDisablePacket();
    }
}
