package prelude.protocol;

import prelude.protocol.processedresults.PreludePlayerInfo;

import java.util.HashSet;
import java.util.Set;

public abstract class PacketManager {

    protected static Set<ServerBoundPacket> serverBoundPackets = new HashSet<>();

    public abstract ProcessedResult processHandshakeInfo(PreludePlayerInfo info);

    public static ServerBoundPacket getInboundPacketFromString(String string) {
        for (ServerBoundPacket packet : serverBoundPackets) {
            if (packet.getPattern().matcher(string.trim().toLowerCase()).matches()) {
                return packet.createNewInstanceWithData(string);
            }
        }

        return null;
    }
}
