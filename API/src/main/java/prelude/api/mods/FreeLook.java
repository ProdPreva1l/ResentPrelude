package prelude.api.mods;

import prelude.api.ResentMod;

public abstract class FreeLook extends ResentMod {
    @Override
    public final String getReceiverId() {
        return "free_look";
    }
}
