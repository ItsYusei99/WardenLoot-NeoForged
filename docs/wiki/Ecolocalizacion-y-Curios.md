# Curios y ecolocalización

## Warden Ears / Warden Tendrils

El accesorio **Warden Tendrils** (`wardenlootforge:warden_ears_trinket`) ocupa el slot `HEAD` de Curios. Curios es una dependencia requerida en cliente y servidor.

Cuando un jugador lleva el accesorio, los sonidos de entidades cercanas pueden aplicarles **Glowing** durante 3 segundos. El rango se basa en el rango del sonido y en `trinketRangeMultiplier`.

## Efecto Echolocation

Warden Blood da el efecto Echolocation durante 60 segundos. Mientras está activo, habilita la misma detección por sonidos sin tener que llevar el accesorio.

## Opciones

- `trinketCosmeticOnly`: desactiva el resaltado aplicado por el manejador del servidor cuando es `true`.
- `trinketRangeMultiplier`: multiplica el rango de detección; valor predeterminado `1.0`.
