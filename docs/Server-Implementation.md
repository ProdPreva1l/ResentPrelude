# Server Side Implementation Specs

## General
- All packets are sent over the PluginMessage channels
- All channels use the `resent:` namespace (Example: `resent:totem_tweaks`)
- Your plugin must depend on `BukkitResentAPI`
- You must run `ResentAPI.getInstance().validateConnection(Actor actor)` sometime in the connection phase, preferably the on join event to avoid conflicts

All methods that require a player use the `Actor` object
you can obtain an Actor by adding [BukkitAdapter.java](/bukkit/src/main/java/info/preva1l/resentclientapi/BukkitAdapter.java) to your project
or more simply by using `ResentAPI.getInstance().getActor(playersUUID)`

## Mod Details
- All mods have the same base data, these are `init`, `disable` or an empty data field
- If the server sends a `disable` packet the client will forcibly disable the mod until the user disconnects from the server
- The `init` packet is not a required to be handled client side, it is used to signify to the client that the server has a hook for that mod. It should be sent on the join event.

## Mod Specific Details
In this section you will find details that are specific to each mod

### OffHand (Mod ID: `offhand`)
<details>
<summary><strong>Caveat with the default implementation</strong></summary>

There are some caveats with this mod, when the server is using the base implementation this packet will only get sent every `0.5s` (`10 ticks`).<br/>
The reason for this is that there is no singular guaranteed method to check if the offhand item has been changed.<br/>
So we run a task timer to see if the value has changed from last iteration, this also (marginally) reduces memory usage client side.<br/>
You only have to construct an item stack with the material type and enchant it with a random enchant to display as those are the only things that matter to display the item
</details>

- The offhand mod needs to notify the client everytime the player has a new item in their offhand, including if the item is air, how you do this is up to you

### TotemTweaks (Mod ID: `totem_tweaks`)
It was requested that I make this fire only if the totem was in the secondary hand but there is no way of checking this, if you find a way feel free to make a PR.

- Your implementation should ignore cancelled to avoid weirdness
- Your implementation should have Highest priority so it runs last to avoid conflicts
- You should only fire this method on the `EntityResurrectEvent` but there is rare cases where you may need to fire it elsewhere (Example: custom dungeons)