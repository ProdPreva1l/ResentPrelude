package prelude;

import prelude.adapter.BukkitPlayerAdapter;
import prelude.api.packet.PacketManager;
import prelude.api.packet.ProcessedResult;
import prelude.api.packet.processedresults.PreludePlayerInfo;

public class BukkitPacketManager extends PacketManager {
    @Override
    public ProcessedResult processHandshakeInfo(PreludePlayerInfo info) {
        BukkitPlayerAdapter.registerInfo(info.getUsername(), info);
        return info;
    }
}
