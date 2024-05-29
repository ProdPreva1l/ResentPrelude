package prelude.protocol;

public abstract class ClientBoundPacket extends Packet {
    public static final String GENERIC_PACKET_FORMAT =
            "{" +
                    "\"packet_receiver\":\"%packet_receiver%\"," +
                    "\"message\":\"%message%\"" +
            "}";

    public abstract String serialize();
}
