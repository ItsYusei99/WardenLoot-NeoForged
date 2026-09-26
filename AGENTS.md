# Warden Loot — NeoForge 1.21.1

## Architecture

See the [interactive system map](docs/architecture/wardenloot-neoforge-1.21.1.html). Its source specification is `docs/architecture/wardenloot-neoforge-1.21.1.architecture.json`.

## Project layout

- `neoforge/` is the Minecraft 1.21.1 / NeoForge 21.1.249 port.
- `forge/` and `fabric/` are the upstream implementations and asset sources; keep them intact when changing the NeoForge port.
- Keep the mod ID and item namespace `wardenlootforge` stable.

## Build and verification

Build from `neoforge/` with Gradle 9.2.1 and Java 21:

```bash
gradle --no-daemon clean build
```

The full-pack startup and Warden-loot smoke test was run in a disposable server directory, separate from any live world.

## NeoForge registration lifecycle

- Construct deferred items lazily through `DeferredRegister` suppliers. In particular, create `MobEffectInstance` for custom effects only after the effect holder is bound.
- Common `ModConfigSpec` values may not be loaded during registry events. Use `WardenLootConfig.getValueOrDefault` for values needed while entries are being registered; runtime handlers may read loaded values normally.
- Armor material and item attribute data captured during registration use spec defaults if the config is not loaded yet. Preserve this behavior unless replacing it with a design that applies custom config values after config loading.
