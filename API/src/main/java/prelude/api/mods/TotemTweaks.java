package prelude.api.mods;

import prelude.api.PreludePlayer;
import prelude.api.ResentMod;

public abstract class TotemTweaks extends ResentMod {

    protected TotemTweaks() {
        super();
        dataRegistry.put("totem_popped", "totem_consumed");
    }

    public void sendTotemPoppedEvent(PreludePlayer preludePlayer) {
        preludePlayer.sendPacket(this.getModId(), dataRegistry.get("totem_popped"));
    }

    @Override
    public final String getModId() {
        return "totem_tweaks";
    }
}
