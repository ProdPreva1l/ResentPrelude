package prelude.api.packet.processedresults;

import prelude.api.packet.ProcessedResult;
import prelude.api.packet.packets.inbound.HandshakePacket;

import java.util.Arrays;
import java.util.Objects;

public final class PreludePlayerInfo extends ProcessedResult {
    public static final PreludePlayerInfo UNKNOWN_INFO = new PreludePlayerInfo(null, null, null, null, false, null);

    private final String username;
    private final String resentVersion;
    private final String resentPatchNum;
    private final String clientType;
    private final boolean isRankedPlayer;
    private final String[] enabledMods;

    public PreludePlayerInfo(String username, String resentVersion, String resentPatchNum, String clientType, boolean isRankedPlayer, String[] enabledMods) {
        this.username = username;
        this.resentVersion = resentVersion;
        this.resentPatchNum = resentPatchNum;
        this.clientType = clientType;
        this.isRankedPlayer = isRankedPlayer;
        this.enabledMods = enabledMods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreludePlayerInfo)) return false;
        PreludePlayerInfo that = (PreludePlayerInfo) o;
        return isRankedPlayer == that.isRankedPlayer && Objects.equals(resentVersion, that.resentVersion) &&
                Objects.equals(resentPatchNum, that.resentPatchNum) && Objects.equals(clientType, that.clientType) &&
                Objects.deepEquals(enabledMods, that.enabledMods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resentVersion, resentPatchNum, clientType, isRankedPlayer, Arrays.hashCode(enabledMods));
    }

    @Override
    public String toString() {
        return "PreludePlayerInfo:" + HandshakePacket.HANDSHAKE_PACKET_FORMAT
                        .replace("%resVer%", resentVersion)
                        .replace("%patchNum%", resentPatchNum)
                        .replace("%clientType%", clientType)
                        .replace("%isRankedPlayer%", isRankedPlayer + "")
                        .replace("%modsOn%",
                                Arrays.toString(enabledMods).substring(1, enabledMods.length - 1));
    }

    public String getUsername() {
        return username;
    }

    public String getResentVersion() {
        return resentVersion;
    }

    public String getResentPatchNum() {
        return resentPatchNum;
    }

    public String getClientType() {
        return clientType;
    }

    public boolean isRankedPlayer() {
        return isRankedPlayer;
    }

    public String[] getEnabledMods() {
        return enabledMods;
    }
}
