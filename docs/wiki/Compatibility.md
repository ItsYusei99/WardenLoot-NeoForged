# Compatibility and Known Limitations

- Tested target: Minecraft **1.21.1** with **NeoForge 21.1.249**.
- Curios API **9.5.1+1.21.1** is required on client and server for Warden Tendrils.
- Install the same mod JAR on both sides.
- The mod keeps the `wardenlootforge` ID; do not install the original Warden Loot Forge JAR alongside this port.
- Other Minecraft and NeoForge versions are not supported yet. The repository is version-independent so future ports can be added separately.
- Stats captured during item registration use config defaults if the COMMON config has not loaded yet; see [Configuration](Configuration.md).

The server was tested on NeoForge 21.1.249 with Curios and the Warden loot table. Future versions need separate testing before being listed as supported.
