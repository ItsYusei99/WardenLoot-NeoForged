# Loot and Progression

## Warden death loot

The Warden loot modifier adds four guaranteed Consumed Souls and makes 12 independent 50% rolls for extra souls. It also adds one Warden Heart when either armor feature is enabled.

## Mining sculk blocks

Breaking `minecraft:sculk` has a default `0.006` chance (0.6%) to drop one Consumed Soul. The `sculkDropsSoul` and `soulChanceFromSculk` options control this.

## Creatures killed by a Warden

When `wardenKillsDropSoul` is enabled, creatures killed by a Warden can drop a Consumed Soul:

- Players: 20% chance.
- Other entities: 5% chance.

## Ancient City chests

Each Ancient City chest receives these independent rolls:

- Four Consumed Soul rolls, each with an 8% chance.
- One Imbued Ingot roll with a 6% chance.
- If Bane of the Depth-Keeper is enabled, three enchanted-book rolls, each with a 3% chance. A generated book receives a random level from I to V.

`wardenDropsModLoot` controls Warden death loot. `ancientCityHasModLoot` controls Ancient City chest loot. `registerWardenBaneEnchantment` controls the enchantment, its damage bonus, and its books.
