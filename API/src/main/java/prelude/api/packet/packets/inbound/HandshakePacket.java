package prelude.api.packet.packets.inbound;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import prelude.api.packet.InboundPacket;
import prelude.api.packet.PacketManager;
import prelude.api.packet.ProcessedResult;
import prelude.api.packet.processedresults.PreludePlayerInfo;

import java.util.Objects;
import java.util.regex.Pattern;

public final class HandshakePacket extends InboundPacket {
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

    private PreludePlayerInfo preludePlayerInfo = PreludePlayerInfo.UNKNOWN_INFO;

    public HandshakePacket() {
        super();
        preludePlayerInfo = PreludePlayerInfo.UNKNOWN_INFO;
    }

    public HandshakePacket(String message) {
        PreludePlayerInfo result;

        try {
            JsonElement element = JsonParser.parseString(message);
            JsonObject json = element.getAsJsonObject();
            result = new PreludePlayerInfo(
                    json.get("username").getAsString(),
                    json.get("resent-version").getAsString(),
                    json.get("patch-num").getAsString(),
                    json.get("client-type").getAsString(),
                    Boolean.parseBoolean(json.get("is-ranked-player").getAsString()),
                    json.get("enabled-mods").getAsString().split(",")
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
    protected HandshakePacket createNewInstanceWithData(String data) {
        return new HandshakePacket(data);
    }

    @Override
    protected Pattern getPattern() {
        return Pattern.compile(HANDSHAKE_PACKET_REGEX, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public int hashCode() {
        return preludePlayerInfo == null ? 0 : preludePlayerInfo.hashCode();
    }

    @Override
    public String toString() {
        return preludePlayerInfo == null ? "HandshakePacket:null" :
                "HandshakePacket:" + preludePlayerInfo.toString().substring(17);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HandshakePacket)) return false;
        HandshakePacket that = (HandshakePacket) o;
        return Objects.equals(preludePlayerInfo, that.preludePlayerInfo);
    }
}