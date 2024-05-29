package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;
import prelude.network.PacketManager;
import prelude.network.packets.outbound.AnchorRendererPacket;
import prelude.network.packets.outbound.AnchorRendererPacket.AnchorRendererPacketBuilder;

public abstract class AnchorRenderer extends ResentMod {
    protected AnchorRenderer() {
        super();
    }

    public void sendPlacedAnchorPacket(PreludePlayer preludePlayer, int x, int y, int z) {
        sendInteractedAnchorPacket(preludePlayer, x, y, z, 0);
    }

    /**
     * @param charge 1 to 3, describing the amount of glowstone in the anchor
     */
    public void sendInteractedAnchorPacket(PreludePlayer preludePlayer, int x, int y, int z, int charge) {
        AnchorRendererPacketBuilder builder = (AnchorRendererPacketBuilder)
                PacketManager.getOutboundPacketBuilder(AnchorRendererPacket.class);

        preludePlayer.sendPacket(
                builder
                        .x(x)
                        .y(y)
                        .z(z)
                        .charge(charge)
                        .receiver(this.getReceiverId())
                        .build()
        );
    }

    public void sendBlownUpAnchorPacket(PreludePlayer preludePlayer, int x, int y, int z) {
        sendInteractedAnchorPacket(preludePlayer, x, y, z, -1);
    }

    @Override
    public final String getReceiverId() {
        return "anchor_renderer";
    }
}
