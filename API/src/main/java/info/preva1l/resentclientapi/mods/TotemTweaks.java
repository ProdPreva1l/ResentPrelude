package info.preva1l.resentclientapi.mods;

import info.preva1l.resentclientapi.Actor;
import info.preva1l.resentclientapi.ResentMod;

public abstract class TotemTweaks extends ResentMod {

    protected TotemTweaks() {
        super();
        dataRegistry.put("totem_popped", "totem_consumed".getBytes());
    }

    public abstract void sendTotemPoppedEvent(Actor actor);

    @Override
    public String getChannel() {
        return "resent:totem_tweaks";
    }
}
