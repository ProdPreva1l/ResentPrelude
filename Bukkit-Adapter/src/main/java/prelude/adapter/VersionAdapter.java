package prelude.adapter;

import prelude.api.mods.AnchorRenderer;
import prelude.api.mods.OffHand;
import prelude.api.mods.TotemTweaks;


public interface VersionAdapter {

    /**
     * Register the anchor mod listeners
     * @param anchorMod the instance of the mod to use
     */
    void registerAnchorListener(AnchorRenderer anchorMod);

    /**
     * Register the totem tweaks mod listeners
     * @param totemMod the instance of the mod to use
     */
    void registerTotemListener(TotemTweaks totemMod);

    /**
     * Get the runnable for the offhand mod.
     * @return Runnable
     */
    Runnable getOffHandRunnable(OffHand offhandMod);

    /**
     * Check whether the adapter supports anchor renderer mod.
     * @return true if it does
     * @apiNote Anchors are in MC Version 1.16+
     */
    default boolean hasAnchorSupport() {
        return false;
    }

    /**
     * Check whether the adapter supports totem tweaks mod.
     * @return true if it does
     * @apiNote Totems are in MC Version 1.11+
     */
    default boolean hasTotemSupport() {
        return false;
    }

    /**
     * Check whether the adapter supports offhand mod.
     * @return true if it does
     * @apiNote OffHand is in MC Version 1.9+
     */
    default boolean hasOffHandSupport() {
        return false;
    }

    /**
     * Sends messages in a channel with all the potion effect ids to prevent the bug
     */
    default void sendPotionEffects() {

    }
}
