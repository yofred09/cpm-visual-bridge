# CPM Visual Bridge

See your **Customizable Player Models** character where other mods would normally render a vanilla player.

CPM Visual Bridge is a client-side compatibility addon for **[Customizable Player Models](https://www.curseforge.com/minecraft/mc-mods/custom-player-models)** on NeoForge 1.21.1. It reconnects external player renderers to CPM while preserving their original poses, animations, equipment, transparency, colors, scale, and visual effects whenever possible.

## Why is this needed?

Many mods render player copies, projections, illusions, or abilities through their own models instead of Minecraft's normal player renderer. Because that bypasses CPM, these effects often display the vanilla player instead of the custom model.

CPM Visual Bridge provides small, isolated compatibility adapters for those renderers. If an integration is absent or incompatible, it safely falls back to the original renderer instead of breaking the effect.

## Current integrations

- **[Cybernetics](https://www.curseforge.com/minecraft/mc-mods/cybernetics):** CPM models and Pehkui scale in Sandevistan mirages and Holoprojector projections
- **[Relics](https://www.curseforge.com/minecraft/mc-mods/relics-mod):** CPM appearance for Glitchy Illusion entities while preserving their pose, equipment, transparency, colors, and glitch effect
- **[Mowzie's Mobs](https://www.curseforge.com/minecraft/mc-mods/mowzies-mobs):** CPM models during supported third-person ability animations

Mowzie's Mobs first-person ability arms remain under development because they use a separate GeckoLib hand renderer.

## Features

- Client-side only
- Works in singleplayer and multiplayer
- Target mods are optional
- Safe fallback to each mod's original renderer
- Preserves supported poses, animations, held items, colors, transparency, and effects
- Preserves Pehkui player scale in supported projections
- Adaptive Sandevistan model density to protect client performance
- Individual configuration options for every integration
- Extensible adapter-based design for future compatibility

## Requirements

- Minecraft 1.21.1
- NeoForge
- **[Customizable Player Models](https://www.curseforge.com/minecraft/mc-mods/custom-player-models)**

Cybernetics, Relics, Mowzie's Mobs, and Pehkui are optional. Install only the target mods whose integrations you want to use.

The addon must be installed on each client that should display CPM models in supported external renderers. It is not required on a dedicated server.

## Configuration

Every integration can be enabled or disabled independently in:

`config/cpmvisualbridge-client.toml`

Adaptive Sandevistan rendering also has a separate performance option.

## Support and compatibility requests

When reporting a problem, include the Minecraft, NeoForge, CPM, and affected target-mod versions, the CPM model type, whether it happens in first or third person, `latest.log`, and a screenshot or short recording when possible.

- **[Source code and issue tracker](https://github.com/yofred09/cpm-visual-bridge)**
- **[Official Discord community](https://discord.gg/R5VnN7Rn5H)**

## Credits

- Customizable Player Models and its contributors
- The developers of Cybernetics, Relics, Mowzie's Mobs, and Pehkui
- CPM Visual Bridge by Yo_Fred
