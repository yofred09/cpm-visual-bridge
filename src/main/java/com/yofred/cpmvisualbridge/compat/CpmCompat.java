package com.yofred.cpmvisualbridge.compat;

import com.yofred.cpmvisualbridge.CpmVisualBridge;
import net.minecraft.client.player.AbstractClientPlayer;
import net.neoforged.fml.ModList;

import java.util.concurrent.atomic.AtomicBoolean;

/** Crash-safe facade around CPM's optional rendering API. */
public final class CpmCompat {
    public static final String MOD_ID_CPM = "cpm";
    public static final String MOD_ID_LEGACY = "customplayermodels";

    private static final AtomicBoolean FAILURE_LOGGED = new AtomicBoolean();
    private static volatile boolean bridgeAttempted;
    private static volatile boolean bridgeReady;
    private static volatile boolean apiFeaturesDisabled;

    private CpmCompat() {}

    public static void initialize() {
        ensureBridge();
    }

    public static boolean isLoaded() {
        try {
            return ModList.get().isLoaded(MOD_ID_CPM) || ModList.get().isLoaded(MOD_ID_LEGACY);
        } catch (Throwable error) {
            return false;
        }
    }

    public static boolean isApiAvailable() {
        ensureBridge();
        return bridgeReady && !apiFeaturesDisabled && CpmSoftBridge.isApiAvailable();
    }

    public static Boolean hasCustomModel(AbstractClientPlayer player) {
        if (player == null || !isLoaded()) return false;
        ensureBridge();
        return bridgeReady ? CpmSoftBridge.hasCustomModelSafe(player) : null;
    }

    public static boolean areApiFeaturesDisabled() {
        return apiFeaturesDisabled;
    }

    private static synchronized void ensureBridge() {
        if (bridgeAttempted || apiFeaturesDisabled || !isLoaded()) return;
        bridgeAttempted = true;
        try {
            CpmSoftBridge.init();
            bridgeReady = !apiFeaturesDisabled;
        } catch (Throwable error) {
            disableApiFeatures("initialization", error);
        }
    }

    static void disableApiFeatures(String where, Throwable error) {
        apiFeaturesDisabled = true;
        bridgeReady = false;
        if (FAILURE_LOGGED.compareAndSet(false, true)) {
            CpmVisualBridge.LOGGER.warn(
                    "CPM integration disabled after failure at {}; original renderers remain active",
                    where,
                    error
            );
        }
    }
}
