package prelude.api.packet;

import java.util.regex.Pattern;

public abstract class Packet {
    protected Packet() {
        PacketManager.packets.add(this);
    }

    protected abstract Packet createNewInstanceWithData(String data);
    protected abstract Pattern getPattern();

    public abstract ProcessedResult processPacket(PacketManager manager);

    public abstract String toString();
    public abstract boolean equals(Object o);
    public abstract int hashCode();
}
