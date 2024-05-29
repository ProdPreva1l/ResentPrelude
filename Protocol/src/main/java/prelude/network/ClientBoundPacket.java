package prelude.network;

public abstract class ClientBoundPacket extends Packet {
    public static final String GENERIC_PACKET_FORMAT =
            "{" +
                    "\"packet_receiver\":\"%packet_receiver%\"," +
                    "\"message\":\"%message%\"" +
            "}";

    protected String receiver;

    protected ClientBoundPacket(Class<? extends ClientBoundPacket> clazz) {
        PacketManager.clientBoundPacketClassToBuilderMap.put(clazz, builder());
    }

    protected ClientBoundPacket() {}

    public abstract String serialize();
    public abstract ClientBoundPacketBuilder<? extends ClientBoundPacket> builder();
}
