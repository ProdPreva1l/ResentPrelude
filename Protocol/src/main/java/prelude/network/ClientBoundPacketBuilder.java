package prelude.network;

public abstract class ClientBoundPacketBuilder<E extends ClientBoundPacket> {
    protected String receiver;

    public ClientBoundPacketBuilder<E> receiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public abstract E build();
    public abstract boolean equals(Object o);
}
