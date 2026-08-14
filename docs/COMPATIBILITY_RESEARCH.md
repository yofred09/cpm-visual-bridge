# Compatibility research

## Relics

`GlitchyIllusionRenderer` owns separate wide and slim `PlayerModel` instances and renders both the coloured body and aura directly. It never enters the normal `PlayerRenderer`, so CPM does not receive the usual player render callbacks.

The bridge redirects only those two model passes. It resolves the illusion owner, applies the owner's optional Pehkui scale, asks CPM to render the same prepared `PlayerModel`, and falls back to Relics' original model pass on any unavailable capability or failure. Relics remains responsible for snapshots, animation, held items, colours, lighting, aura and gameplay.

## Mowzie's Mobs

Mowzie's Mobs 1.21.1 replaces normal first- and third-person player rendering while an ability is active. `ClientEventHandler` cancels the regular player/hand render and delegates to `GeckoRenderPlayer` or `GeckoFirstPersonRenderer`. Those renderers use GeckoLib geometry and later copy bone transforms into a vanilla `PlayerModel` for layers.

This is a valid CPM integration target, but it is not equivalent to an entity illusion redirect. The third-person bridge wraps only GeckoLib's visible geometry call after Mowzie has prepared its ability pose and particle anchor bones. It copies those bone transforms into Mowzie's animated `PlayerModel`, renders CPM with that model as the parent pose, and invokes the original Gecko renderer if CPM is unavailable or the player has no custom model.

The dedicated first-person hand renderer remains pending because it uses a different Gecko model and item transform pipeline. It must be integrated separately rather than reusing the third-person hook.

Source reviewed: `BobMowzie/MowziesMobs-Public`, branch `main`, Minecraft 1.21.1 / mod 1.8.2.
