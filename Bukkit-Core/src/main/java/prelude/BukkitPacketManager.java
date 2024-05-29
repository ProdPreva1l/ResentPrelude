package prelude;

import prelude.adapter.BukkitPlayerAdapter;
import prelude.network.PacketManager;
import prelude.network.ProcessedResult;
import prelude.network.processedresults.PreludePlayerInfo;

public class BukkitPacketManager extends PacketManager {
    @Override
    public ProcessedResult processHandshakeInfo(PreludePlayerInfo info) {
        BukkitPlayerAdapter.registerInfo(info.getUsername(), info);
        return info;
    }
}
