package com.yofred.cpmvisualbridge.mixin;

import net.neoforged.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/** Applies each integration only while its target mod is installed. */
public final class OptionalModMixinPlugin implements IMixinConfigPlugin {
    @Override public void onLoad(String mixinPackage) {}
    @Override public String getRefMapperConfig() { return null; }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String modId = mixinClassName.contains(".relics.") ? "relics"
                : mixinClassName.contains(".mowzies.") ? "mowziesmobs"
                : mixinClassName.contains(".cybernetics.") ? "createcybernetics"
                : null;
        if (modId == null) return false;
        try {
            var list = LoadingModList.get();
            return list != null && list.getModFileById(modId) != null;
        } catch (Throwable ignored) {
            return false;
        }
    }

    @Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}
    @Override public List<String> getMixins() { return null; }
    @Override public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
    @Override public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
