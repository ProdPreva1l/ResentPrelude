package prelude.api.packet.packets;

import org.json.JSONObject;
import prelude.api.packet.PacketManager;
import prelude.api.packet.ProcessedResult;
import prelude.api.packet.processedresults.PreludePlayerInfo;
import prelude.api.packet.Packet;

import java.util.Objects;
import java.util.regex.Pattern;

public final class ResentHandshakePacket extends Packet {
    public static final String HANDSHAKE_PACKET_FORMAT =
            "{" +
                    "\"username\":\"%username%\"," +
                    "\"resent-version\":\"%resVer%\"," +
                    "\"patch-num\":\"%patchNum%\"," +
                    "\"client-type\":\"%clientType%\"," +
                    "\"is-ranked-player\":\"%isRankedPlayer%\"," +
                    "\"enabled-mods\":\"%modsOn%\"" +
            "}";

    public static final String HANDSHAKE_PACKET_REGEX =
            "\\{" +
                    "\"username\":\".+\"," +
                    "\"resent-version\":\".+\"," +
                    "\"patch-num\":\".+\"," +
                    "\"client-type\":\".+\"," +
                    "\"is-ranked-player\":\".+\"," +
                    "\"enabled-mods\":\".+\"" +
            "}";

    private final PreludePlayerInfo preludePlayerInfo;

    public ResentHandshakePacket() {
        super();
        preludePlayerInfo = PreludePlayerInfo.UNKNOWN_INFO;
    }

    public ResentHandshakePacket(String message) {
        PreludePlayerInfo result;

        try {
            JSONObject json = new JSONObject(message);
            result = new PreludePlayerInfo(
                    json.getString("username"),
                    json.getString("resent-version"),
                    json.getString("patch-num"),
                    json.getString("client-type"),
                    Boolean.parseBoolean(json.getString("is-ranked-player")),
                    json.getString("enabled-mods").split(",")
            );
        } catch (Exception e) {
            result = PreludePlayerInfo.UNKNOWN_INFO;
        }

        preludePlayerInfo = result;
    }

    @Override
    public ProcessedResult processPacket(PacketManager manager) {
        return manager.processHandshakeInfo(preludePlayerInfo);
    }

    @Override
    protected Packet createNewInstanceWithData(String data) {
        return new ResentHandshakePacket(data);
    }

    @Override
    protected Pattern getPattern() {
        return Pattern.compile(HANDSHAKE_PACKET_REGEX, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public int hashCode() {
        return preludePlayerInfo.hashCode();
    }

    @Override
    public String toString() {
        return preludePlayerInfo.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResentHandshakePacket)) return false;
        ResentHandshakePacket that = (ResentHandshakePacket) o;
        return Objects.equals(preludePlayerInfo, that.preludePlayerInfo);
    }

}