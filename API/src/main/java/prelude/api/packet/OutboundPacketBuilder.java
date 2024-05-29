package prelude.api.packet;

public abstract class OutboundPacketBuilder<E extends OutboundPacket> {
    public abstract E build();
    public abstract boolean equals(Object o);
    public abstract int hashCode();
    public abstract String toString();
}
