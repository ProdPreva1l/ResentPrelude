package prelude.api.packet;

public abstract class Packet {
    protected abstract Packet createNewInstanceWithData(String data);

    public abstract ProcessedResult processPacket(PacketManager manager);

    public abstract String toString();
    public abstract boolean equals(Object o);
    public abstract int hashCode();
}
