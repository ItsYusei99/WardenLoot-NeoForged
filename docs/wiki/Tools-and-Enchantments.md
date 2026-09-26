# Tools and Enchantments

Imbued tools are upgraded from their matching netherite tool with a Netherite Upgrade Smithing Template and an Imbued Ingot.

| Tool | Special behavior |
|---|---|
| Imbued Sword | Hits inflict Darkness on the target for 10 seconds. |
| Imbued Pickaxe | Mines deepslate four times faster than its normal tool speed. |
| Imbued Axe | Hits inflict Darkness on the target for 10 seconds. |
| Imbued Hoe | High-tier farming tool. |
| Imbued Shovel | High-tier digging tool. |

Default tool values are mining level 4, durability 3070, speed 12, base material damage bonus 8, and enchantment value 20. These are configurable; final attack damage also depends on each tool's attributes.

## Bane of the Depth-Keeper

This main-hand weapon enchantment has up to level V and increases damage against the Warden. Its event adds:

```text
(10 + 6 × level) × wardenBaneEnchantmentMultiplier
```

With the default multiplier of 1, that is 16 extra damage at level I and 40 at level V. Enchanted books can appear in Ancient City chests when the enchantment is enabled.
