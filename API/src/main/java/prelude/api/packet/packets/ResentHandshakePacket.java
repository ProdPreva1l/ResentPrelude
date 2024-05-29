package prelude.api.packet.packets;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import prelude.api.packet.InboundPacket;
import prelude.api.packet.PacketManager;
import prelude.api.packet.ProcessedResult;
import prelude.api.packet.processedresults.PreludePlayerInfo;

import java.util.Objects;
import java.util.regex.Pattern;

public final class ResentHandshakePacket extends InboundPacket {
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

    public ResentHandshakePacket() {
        super();
        preludePlayerInfo = PreludePlayerInfo.UNKNOWN_INFO;
    }

    public ResentHandshakePacket(String message) {
        PreludePlayerInfo result;

        try {
            JsonElement element = new JsonParser().parse(message);
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
    protected ResentHandshakePacket createNewInstanceWithData(String data) {
        return new ResentHandshakePacket(data);
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
        return preludePlayerInfo == null ? "null" :preludePlayerInfo.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResentHandshakePacket)) return false;
        ResentHandshakePacket that = (ResentHandshakePacket) o;
        return Objects.equals(preludePlayerInfo, that.preludePlayerInfo);
    }

}