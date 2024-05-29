package prelude.network;

public abstract class OutboundPacket extends Packet {
    public static final String GENERIC_PACKET_FORMAT =
            "{" +
                    "\"packet_receiver\":\"%packet_receiver%\"," +
                    "\"message\":\"%message%\"" +
            "}";

    protected String receiver;

    protected OutboundPacket(Class<? extends OutboundPacket> clazz) {
        PacketManager.outboundPackets.put(clazz, builder());
    }

    protected OutboundPacket() {}

    public abstract String serialize();
    public abstract OutboundPacketBuilder<? extends OutboundPacket> builder();
}
