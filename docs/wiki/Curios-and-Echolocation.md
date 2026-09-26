# Curios and Echolocation

## Warden Ears / Warden Tendrils

The **Warden Tendrils** accessory (`wardenlootforge:warden_ears_trinket`) uses Curios' `HEAD` slot. Curios is required on both the client and server.

While a player wears the accessory, sounds from nearby living entities can make those entities glow for 3 seconds. Detection range is based on the sound's range and `trinketRangeMultiplier`.

## Echolocation effect

Warden Blood grants Echolocation for 60 seconds. While active, it enables the same sound-based detection without the accessory.

## Options

- `trinketCosmeticOnly`: when `true`, disables the server-side glowing effect.
- `trinketRangeMultiplier`: multiplies echolocation range; default is `1.0`.
