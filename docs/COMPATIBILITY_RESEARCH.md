# Compatibility research

## Relics

`GlitchyIllusionRenderer` owns separate wide and slim `PlayerModel` instances and renders both the coloured body and aura directly. It never enters the normal `PlayerRenderer`, so CPM does not receive the usual player render callbacks.

The bridge redirects only those two model passes. It resolves the illusion owner, applies the owner's optional Pehkui scale, asks CPM to render the same prepared `PlayerModel`, and falls back to Relics' original model pass on any unavailable capability or failure. Relics remains responsible for snapshots, animation, held items, colours, lighting, aura and gameplay.

## Mowzie's Mobs

Mowzie's Mobs 1.21.1 replaces normal first- and third-person player rendering while an ability is active. `ClientEventHandler` cancels the regular player/hand render and delegates to `GeckoRenderPlayer` or `GeckoFirstPersonRenderer`. Those renderers use GeckoLib geometry and later copy bone transforms into a vanilla `PlayerModel` for layers.

This is a valid CPM integration target, but it is not equivalent to an entity illusion redirect. A correct implementation must preserve Mowzie ability animation transforms and particle anchor bones while replacing only visible player geometry with CPM. It therefore remains intentionally pending until its Gecko bone-to-player-model stage is isolated and covered by a safe fallback.

Source reviewed: `BobMowzie/MowziesMobs-Public`, branch `main`, Minecraft 1.21.1 / mod 1.8.2.
