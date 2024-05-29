package prelude.api;

import java.util.HashMap;
import java.util.Map;

public abstract class ResentMod {
    protected boolean enabled = false;

    protected Map<String, String> dataRegistry = new HashMap<>();

    protected ResentMod() {
        dataRegistry.put("empty", new String(new byte[0]));
        dataRegistry.put("init", "init");
        dataRegistry.put("disable", "disable");
    }

    public void initMod(PreludePlayer preludePlayer) {
        preludePlayer.sendPacket(this.getModId(), this.getData("init"));
    }

    public void disableMod(PreludePlayer preludePlayer) {
        preludePlayer.sendPacket(this.getModId(), this.getData("disable"));
    }

    public abstract String getModId();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public abstract boolean isAllowed();

    public boolean isOfficiallyHooked() {
        return false;
    }

    public String getData(String key) {
        String data = dataRegistry.get(key);
        if (data == null) {
            throw new IllegalArgumentException("Data key " + key + " not found in registry!");
        }
        return data;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
