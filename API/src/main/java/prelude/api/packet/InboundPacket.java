package prelude.api.packet;

public abstract class InboundPacket extends Packet {
    protected InboundPacket() {
        PacketManager.inboundPackets.add(this);
    }

    @Override
    protected InboundPacket createNewInstanceWithData(String data) {
        return null;
    }
}
