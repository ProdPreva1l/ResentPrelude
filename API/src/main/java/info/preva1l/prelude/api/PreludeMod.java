package info.preva1l.prelude.api;

import java.util.HashMap;
import java.util.Map;

public abstract class PreludeMod {
    protected boolean enabled = false;

    protected Map<String, byte[]> dataRegistry = new HashMap<>();

    protected PreludeMod() {
        dataRegistry.put("empty", new byte[0]);
        dataRegistry.put("init", "init".getBytes());
        dataRegistry.put("disable", "disable".getBytes());
    }

    public void initMod(Actor actor) {
        actor.sendPacket(this.getChannel(), this.getData("init"));
    }

    public void disableMod(Actor actor) {
        actor.sendPacket(this.getChannel(), this.getData("disable"));
    }

    public abstract String getChannel();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public abstract boolean isAllowed();

    public boolean isOfficiallyHooked() {
        return false;
    }

    public byte[] getData(String key) {
        byte[] data = dataRegistry.get(key);
        if (data == null) {
            throw new IllegalArgumentException("Data key " + key + " not found in registry!");
        }
        return data;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
