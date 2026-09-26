# Configuración

NeoForge crea `config/wardenlootforge-common.toml` al iniciar el juego. Los valores indicados son los predeterminados.

## `registry`

| Opción | Predeterminado | Función |
|---|---:|---|
| `registerTools` | `true` | Incluye las herramientas en la pestaña creativa del mod. |
| `registerChestplate` | `true` | Muestra el Heartplate y habilita su efecto salvavidas. |
| `registerHelmetLeggingsBoots` | `true` | Muestra casco, grebas y botas y habilita sus efectos. |
| `registerWardenBaneEnchantment` | `true` | Habilita Bane of the Depth-Keeper, su daño extra y los libros de Ancient City. |

Los toggles controlan la pestaña creativa y las funciones relacionadas; los IDs de los ítems siguen registrados para mantenerlos estables.
El Warden Heart se añade al loot cuando `registerChestplate` o `registerHelmetLeggingsBoots` está activo.

## `stats`

| Opción | Predeterminado | Función |
|---|---:|---|
| `toolMiningLevel` | `4` | Nivel de minería; 4 corresponde a herramientas de nivel diamante. |
| `toolDurability` | `3070` | Usos/durabilidad base de las herramientas. |
| `toolSpeed` | `12.0` | Velocidad de minería base. |
| `toolBaseDamage` | `8.0` | Bonificación de daño de material para las herramientas. |
| `armorBaseProtection` | `8` | Protección base de la armadura. |
| `armorToughness` | `4.0` | Toughness de la armadura cargada. |
| `wardenBaneEnchantmentMultiplier` | `1.0` | Multiplicador del bono de daño contra el Warden. |

## `lootTables`

| Opción | Predeterminado | Función |
|---|---:|---|
| `sculkDropsSoul` | `true` | Permite que bloques sculk suelten Consumed Souls. |
| `soulChanceFromSculk` | `0.006` | Probabilidad por bloque; `0.006` equivale a 0.6%. |
| `ancientCityHasModLoot` | `true` | Añade loot del mod a cofres de Ancient City. |
| `wardenDropsModLoot` | `true` | Añade Consumed Souls y, si corresponde, un Warden Heart al loot del Warden. |
| `wardenKillsDropSoul` | `true` | Permite que entidades derrotadas por un Warden suelten Consumed Souls. |

## `misc`

| Opción | Predeterminado | Función |
|---|---:|---|
| `trinketCosmeticOnly` | `false` | Si es `true`, desactiva el Glowing aplicado por el manejador del servidor. |
| `trinketRangeMultiplier` | `1.0` | Multiplicador del rango de ecolocalización. |
| `animateArmor` | `true` | Usa la textura animada del set; el port incluye el archivo de animación vanilla. |

### Nota sobre estadísticas

NeoForge puede registrar los ítems antes de cargar la configuración común. Las estadísticas capturadas al construir materiales o atributos usan los valores predeterminados durante ese registro. Los ajustes de gameplay que se consultan en tiempo de ejecución leen la configuración cargada normalmente.
