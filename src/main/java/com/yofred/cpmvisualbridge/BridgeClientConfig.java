package com.yofred.cpmvisualbridge;

import net.neoforged.neoforge.common.ModConfigSpec;

/** Per-integration client toggles; disabled integrations use the original renderer. */
public final class BridgeClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue CYBERNETICS = BUILDER
            .comment("Enable CPM/Pehkui rendering for Cybernetics projections and mirages.")
            .define("integrations.cybernetics", true);
    public static final ModConfigSpec.BooleanValue ADAPTIVE_SANDEVISTAN = BUILDER
            .comment("Reduce full CPM copies in long Sandevistan trails to protect frame rate.")
            .define("performance.adaptiveSandevistanDensity", true);
    public static final ModConfigSpec.BooleanValue RELICS = BUILDER
            .comment("Enable CPM rendering for Relics Glitchy Illusions.")
            .define("integrations.relics", true);
    public static final ModConfigSpec.BooleanValue MOWZIES_MOBS = BUILDER
            .comment("Enable CPM rendering for Mowzie's Mobs third-person ability animations.")
            .define("integrations.mowziesMobs", true);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private BridgeClientConfig() {}
}
