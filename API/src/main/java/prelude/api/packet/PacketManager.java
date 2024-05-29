package prelude.api.packet;

import prelude.api.packet.packets.ResentHandshakePacket;
import prelude.api.packet.processedresults.PreludePlayerInfo;

import java.util.HashSet;
import java.util.Set;

public abstract class PacketManager {
    protected static Set<Packet> packets = new HashSet<>();

    public abstract ProcessedResult processHandshakeInfo(PreludePlayerInfo info);

    public static Packet getPacketFromString(String string) {
        for (Packet packet : packets)
            if (packet.getPattern().matcher(string.trim().toLowerCase()).matches())
                return packet.createNewInstanceWithData(string);

        return null;
    }

    public static void initPackets() {
        new ResentHandshakePacket();
    }
}
