package com.yofred.cpmvisualbridge;

import com.mojang.logging.LogUtils;
import com.yofred.cpmvisualbridge.compat.CpmCompat;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(value = CpmVisualBridge.MOD_ID, dist = Dist.CLIENT)
public final class CpmVisualBridge {
    public static final String MOD_ID = "cpmvisualbridge";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CpmVisualBridge(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, BridgeClientConfig.SPEC);
        CpmCompat.initialize();
        LOGGER.info("CPM Visual Bridge ready");
    }
}
