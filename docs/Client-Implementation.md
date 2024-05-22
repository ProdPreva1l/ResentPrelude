# Client Side Implementation Specs

## General
- All packets are sent over the PluginMessage channels
- All channels use the `resent:` namespace (Example: `resent:totem_tweaks`)
- Because we use the PluginChannel, all packets are `Play` packets

## Mod Details
- All mods have the same base data, these are `init`, `disable` or an empty data field
- If the server sends a `disable` packet you are required to respect that and forcibly disable the mod until the user disconnects from the server
- The `init` packet is not a required to be handled, it is used to signify to the client that the server has a hook for that mod. It is sent on the server side join event.
- Rarely a mod will send an `init` and a `disable` packet, in this case the mod must respect the `disable` packet

## Mod Specific Details
In this section you will find details that are specific to each mod

### OffHand (Mod ID: `offhand`)
<details>
<summary><strong>Caveat with the server side implementation</strong></summary>


There are some caveats with this mod, when the server is using the base implementation this packet will only get sent every `0.5s` (`10 ticks`).<br/>
The reason for this is that there is no singular guaranteed method to check if the offhand item has been changed.<br/>
So we run a task timer to see if the value has changed from last iteration, this also (marginally) reduces memory usage client side.<br/>
You only have to construct an item stack with the material type and enchant it with a random enchant to display as those are the only things that matter to display the item
</details>

- The offhand mod requires each packet to contain more detail so the packets are formatted with JSON instead of a raw string
- The offhand mod can send 2 additional packets on top of the base packets
- - The first packet is an Equip packet, the packet is formatted as follows `{"action":"equip_item","item_id":"%item_id%","enchanted":%enchanted%}` where `%item_id%` is replaced with the items enum name as defined in the Bukkit API and `%enchanted%` is replaced with a boolean depending on whether the item has enchants or not 
- - The second packet is an Un-Equip packet, the packet is formatted as follows `{"action":"un-equip_item","item_id":"AIR","enchanted":false}`

### TotemTweaks (Mod ID: `totem_tweaks`)
It was requested that I make this fire only if the totem was in the secondary hand but there is no way of checking this, if you find a way feel free to make a PR.

- The totem tweaks mod sends the `totem_consumed` packet when the server calls the `EntityResurrectEvent`
- Depending on the implementation there is a rare chance that the packet is sent even if the `EntityResurrectEvent` is cancelled, this is plugin compatability issue, this is not the fault of me or you, the base implementation ignores cancelled events and runs last in the handlers chain