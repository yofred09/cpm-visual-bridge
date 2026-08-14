package com.yofred.cpmvisualbridge;

import com.mojang.logging.LogUtils;
import com.yofred.cpmvisualbridge.compat.CpmCompat;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(value = CpmVisualBridge.MOD_ID, dist = Dist.CLIENT)
public final class CpmVisualBridge {
    public static final String MOD_ID = "cpmvisualbridge";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CpmVisualBridge() {
        CpmCompat.initialize();
        LOGGER.info("CPM Visual Bridge ready");
    }
}
