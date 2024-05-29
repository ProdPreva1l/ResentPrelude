package prelude.api.packet;

public abstract class OutboundPacket extends Packet {
    protected OutboundPacket(Class<? extends OutboundPacket> clazz) {
        PacketManager.outboundPackets.put(clazz, builder());
    }

    protected OutboundPacket() {}

    public abstract String serialize();
    public abstract OutboundPacketBuilder<? extends OutboundPacket> builder();
    public String toString() {
        return this.getClass().getSimpleName() + ":" + serialize();
    }
}
