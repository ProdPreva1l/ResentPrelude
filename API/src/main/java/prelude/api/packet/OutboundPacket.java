package prelude.api.packet;

public abstract class OutboundPacket extends Packet {
    public abstract OutboundPacketBuilder<OutboundPacket> builder();
}
