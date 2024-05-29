package prelude.protocol;

import java.util.regex.Pattern;

public abstract class ServerBoundPacket extends Packet {
    protected ServerBoundPacket() {
        PacketManager.serverBoundPackets.add(this);
    }

    public abstract ProcessedResult processPacket(PacketManager manager);

    protected ServerBoundPacket createNewInstanceWithData(String data) {
        return null;
    }

    protected abstract Pattern getPattern();
}
