# Configuration

NeoForge creates `config/wardenlootforge-common.toml` at startup. Values below are defaults.

## `registry`

| Option | Default | Purpose |
|---|---:|---|
| `registerTools` | `true` | Shows the tools in the mod's creative tab. |
| `registerChestplate` | `true` | Shows the Heartplate and enables its lifesaving effect. |
| `registerHelmetLeggingsBoots` | `true` | Shows the helmet, leggings, and boots and enables their related effects. |
| `registerWardenBaneEnchantment` | `true` | Enables Bane of the Depth-Keeper, its damage bonus, and Ancient City books. |

These toggles control creative-tab display and related features; item IDs remain registered for stability. The Warden Heart is added to Warden loot when either armor option is enabled.

## `stats`

| Option | Default | Purpose |
|---|---:|---|
| `toolMiningLevel` | `4` | Mining level; 4 corresponds to diamond-tier blocks. |
| `toolDurability` | `3070` | Base tool durability. |
| `toolSpeed` | `12.0` | Base mining speed. |
| `toolBaseDamage` | `8.0` | Tool material's base damage bonus. |
| `armorBaseProtection` | `8` | Base armor protection. |
| `armorToughness` | `4.0` | Toughness of charged armor. |
| `wardenBaneEnchantmentMultiplier` | `1.0` | Damage multiplier against the Warden. |

## `lootTables`

| Option | Default | Purpose |
|---|---:|---|
| `sculkDropsSoul` | `true` | Allows sculk blocks to drop Consumed Souls. |
| `soulChanceFromSculk` | `0.006` | Per-block chance; `0.006` equals 0.6%. |
| `ancientCityHasModLoot` | `true` | Adds mod loot to Ancient City chests. |
| `wardenDropsModLoot` | `true` | Adds Consumed Souls and, when armor is enabled, a Warden Heart to Warden loot. |
| `wardenKillsDropSoul` | `true` | Allows entities killed by a Warden to drop Consumed Souls. |

## `misc`

| Option | Default | Purpose |
|---|---:|---|
| `trinketCosmeticOnly` | `false` | When `true`, disables server-side glowing from echolocation. |
| `trinketRangeMultiplier` | `1.0` | Multiplies echolocation range. |
| `animateArmor` | `true` | Uses the animated armor texture; this port includes a vanilla animation file. |

### Stat configuration note

NeoForge may register items before loading the common config. Stats captured while constructing armor materials or item attributes therefore use spec defaults during registration. Runtime gameplay settings read the loaded config normally.
