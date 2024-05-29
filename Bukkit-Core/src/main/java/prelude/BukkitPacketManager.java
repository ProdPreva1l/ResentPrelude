package prelude;

import prelude.adapter.BukkitPlayerAdapter;
import prelude.protocol.PacketManager;
import prelude.protocol.ProcessedResult;
import prelude.protocol.processedresults.PreludePlayerInfo;

public class BukkitPacketManager extends PacketManager {
    @Override
    public ProcessedResult processHandshakeInfo(PreludePlayerInfo info) {
        BukkitPlayerAdapter.registerInfo(info.getUsername(), info);
        return info;
    }
}
