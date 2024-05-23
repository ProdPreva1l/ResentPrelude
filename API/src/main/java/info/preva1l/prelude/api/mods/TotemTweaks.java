package info.preva1l.prelude.api.mods;

import info.preva1l.prelude.api.Actor;
import info.preva1l.prelude.api.PreludeMod;

public abstract class TotemTweaks extends PreludeMod {

    protected TotemTweaks() {
        super();
        dataRegistry.put("totem_popped", "totem_consumed".getBytes());
    }

    public void sendTotemPoppedEvent(Actor actor) {
        actor.sendPacket(getChannel(), dataRegistry.get("totem_popped"));
    }

    @Override
    public String getChannel() {
        return "PRE|TOTT";
    }
}
