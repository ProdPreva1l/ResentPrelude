package prelude.api.mods;

import prelude.api.Actor;
import prelude.api.ResentMod;

public abstract class TotemTweaks extends ResentMod {

    protected TotemTweaks() {
        super();
        dataRegistry.put("totem_popped", "totem_consumed".getBytes());
    }

    public void sendTotemPoppedEvent(Actor actor) {
        actor.sendPacket(getChannel(), dataRegistry.get("totem_popped"));
    }

    @Override
    public final String getChannel() {
        return "PRE|TOTT";
    }
}
