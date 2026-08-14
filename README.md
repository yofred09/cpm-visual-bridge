# CPM Visual Bridge

Client-side compatibility for [Customizable Player Models](https://www.curseforge.com/minecraft/mc-mods/custom-player-models) on NeoForge 1.21.1.

CPM Visual Bridge makes player-like entities, illusions and projections from other mods use the owning player's CPM model while preserving the original effect renderer and falling back safely when an integration is unavailable.

## Initial integrations

- Cybernetics Sandevistan mirages and Holoprojector projections
- Relics Glitchy Illusion entities
- Mowzie's Mobs third-person ability animations

Target mods are optional. CPM is required. The addon is client-side only.

Mowzie's first-person ability arms remain under active development; their dedicated GeckoLib hand renderer requires a separate transform bridge.

Each integration can be disabled independently in `config/cpmvisualbridge-client.toml`. Adaptive Sandevistan density also has its own performance toggle.

## Status

Early development. No public binary release is available yet.
