package prelude.api.packet;

import java.util.regex.Pattern;

public abstract class InboundPacket extends Packet {
    protected InboundPacket() {
        PacketManager.inboundPackets.add(this);
    }

    @Override
    protected InboundPacket createNewInstanceWithData(String data) {
        return null;
    }
    protected abstract Pattern getPattern();
}
