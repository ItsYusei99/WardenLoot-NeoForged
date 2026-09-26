# Loot y progresión

## Loot al matar un Warden

El loot modifier añade cuatro Consumed Souls garantizadas y hace 12 comprobaciones independientes con 50% de probabilidad cada una para añadir almas adicionales. También añade un Warden Heart si alguna de las opciones de armadura está habilitada.

## Minar bloques de sculk

Al romper un bloque `minecraft:sculk`, existe una probabilidad predeterminada de `0.006` —0.6%— de obtener una Consumed Soul. Se controla con `sculkDropsSoul` y `soulChanceFromSculk`.

## Enemigos derrotados por un Warden

Si la opción `wardenKillsDropSoul` está activa, los enemigos que mata un Warden tienen una probabilidad de soltar una Consumed Soul:

- Jugadores: 20%.
- Otras entidades: 5%.

## Cofres de Ancient City

Cada cofre de Ancient City puede recibir:

- Cuatro comprobaciones de Consumed Soul, cada una con 8% de probabilidad.
- Una comprobación de Imbued Ingot, con 6% de probabilidad.
- Si Bane of the Depth-Keeper está habilitado, tres comprobaciones de libro, cada una con 3% de probabilidad. El libro recibe un nivel aleatorio de I a V.

Las opciones `wardenDropsModLoot` y `ancientCityHasModLoot` controlan los dos primeros loot modifiers. `registerWardenBaneEnchantment` controla el encantamiento, su daño y los libros de Ancient City.
