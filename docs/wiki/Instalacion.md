# Instalación

## Requisitos

- Minecraft **1.21.1** con **NeoForge 21.1.249**.
- **Curios API 9.5.1+1.21.1** instalado tanto en el cliente como en el servidor dedicado.
- El mismo JAR de WardenLoot NeoForged en ambos lados.

## Pasos

1. Instala NeoForge para Minecraft 1.21.1.
2. Instala Curios API en las carpetas `mods/` del cliente y del servidor.
3. Copia `wardenlootforge-1.1.3-neoforge-1.21.1.jar` a ambas carpetas `mods/`.
4. Inicia el cliente y el servidor.

El mod necesita Curios para el accesorio Warden Ears; si falta, NeoForge detendrá la carga por una dependencia requerida.

## Compilar desde el código

Se requieren Java 21 y Gradle 9.2.1. Desde `neoforge/`:

```bash
gradle --no-daemon clean build
```

El JAR queda en `neoforge/build/libs/wardenlootforge-1.1.3-neoforge-1.21.1.jar`.
