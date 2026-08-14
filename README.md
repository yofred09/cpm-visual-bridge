# CPM Visual Bridge

Universal client-side compatibility bridge for [Customizable Player Models](https://www.curseforge.com/minecraft/mc-mods/custom-player-models) on NeoForge 1.21.1.

Its goal is simple: when another mod renders a player, a copy of a player, custom ability animations or another player-shaped effect, you should still see that player's CPM model in both singleplayer and multiplayer.

Minecraft mods do not share one universal player-rendering API, so compatibility is provided through small, isolated adapters. Each adapter reconnects an external renderer to CPM while preserving the original mod's pose, animation, items, colors, transparency and special effects. Unsupported or incompatible renderers fall back safely to their original appearance instead of breaking rendering.

## Supported integrations

- Cybernetics Sandevistan mirages and Holoprojector projections
- Relics Glitchy Illusion entities
- Mowzie's Mobs third-person ability animations

Target mods are optional. CPM is required. The addon is client-side only.

Mowzie's first-person ability arms remain under active development; their dedicated GeckoLib hand renderer requires a separate transform bridge.

Each integration can be disabled independently in `config/cpmvisualbridge-client.toml`. Adaptive Sandevistan density also has its own performance toggle.

## Community & Support

Join the [official Discord community](https://discord.gg/R5VnN7Rn5H) for support, compatibility requests, testing and development updates.

## Status

Early development. No public binary release is available yet.
