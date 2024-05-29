package prelude.network;

public abstract class OutboundPacketBuilder<E extends OutboundPacket> {
    protected String receiver;

    public OutboundPacketBuilder<E> receiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public abstract E build();
    public abstract boolean equals(Object o);
}
