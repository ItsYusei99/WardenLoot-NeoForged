# WardenLoot NeoForged

NeoForge port of Warden Loot, maintained by **ItsYusei99**. The current port targets **Minecraft 1.21.1 / NeoForge 21.1.249**. The repository name is version-independent so future NeoForge ports can be added; no other game versions are supported yet.

This project is derived from [Warden Loot by nu11une](https://github.com/nu11une/wardenloot). The upstream Fabric and Forge source/assets are retained where they provide the basis for this port. This project is not an official upstream release.

## Current port

- Warden-themed tools, armor, food, and Warden Ears Curios accessory.
- Warden, sculk-block, and ancient-city loot modifiers.
- NeoForge configuration, recipes, enchantment data, item models, and armor assets.
- Curios API is required on both client and server (`9.5.1+1.21.1` in the tested pack).

## Build

Requirements: Java 21 and Gradle 9.2.1.

```bash
cd neoforge
gradle --no-daemon clean build
```

The mod JAR is written to `neoforge/build/libs/wardenlootforge-1.1.3-neoforge-1.21.1.jar`. Install the same JAR on the client and dedicated server, alongside Curios.

## Architecture

Open the [interactive architecture map](docs/architecture/wardenloot-neoforge-1.21.1.html). Its source specification is `docs/architecture/wardenloot-neoforge-1.21.1.architecture.json`.

## Wiki

Consulta la [guía del mod](docs/wiki/Home.md) para ver el uso de los objetos, equipo, loot, recetas y configuración.

## Versioning

The `neoforge/` Gradle project currently builds only for Minecraft 1.21.1 and NeoForge 21.1.249. Future ports should use version-specific build modules and must be tested independently before being described as supported.

## Known configuration limitation

NeoForge can dispatch item registration before loading the common `ModConfigSpec`. Values needed to construct registry-time armor materials or item attributes therefore fall back to their spec defaults at registration. Runtime gameplay settings read the loaded config normally.

## License and attribution

Licensed under Apache-2.0; see [`LICENSE`](LICENSE) and [`NOTICE`](NOTICE). The upstream copyright and attribution are retained. The NeoForge port and modifications are maintained by ItsYusei99.
