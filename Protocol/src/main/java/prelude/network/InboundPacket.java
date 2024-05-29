package prelude.network;

import java.util.regex.Pattern;

public abstract class InboundPacket extends Packet {
    protected InboundPacket() {
        PacketManager.inboundPackets.add(this);
    }

    public abstract ProcessedResult processPacket(PacketManager manager);
    protected InboundPacket createNewInstanceWithData(String data) {
        return null;
    }
    protected abstract Pattern getPattern();
}
