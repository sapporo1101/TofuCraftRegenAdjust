package io.github.sapporo1101.tofucraftregenadjust.mixin;

import baguchi.tofucraft.item.SoymilkBottleItem;
import baguchi.tofucraft.registry.TofuEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = SoymilkBottleItem.class)
public class MixinSoymilkBottleItem {

    @ModifyArg(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"), index = 0)
    private MobEffectInstance modifyMobEffectInstance(MobEffectInstance mobEffectInstance) {
        final Holder<MobEffect> mobEffect = mobEffectInstance.getEffect();
        int duration = mobEffectInstance.getDuration();
        if (mobEffect == MobEffects.REGENERATION) {
            duration = (int) (Math.log(duration / 20.0 * 2.5) * 20);
        } else if (mobEffect == TofuEffects.SOY_HEALTHY) {
            duration = (int) (Math.log(duration / 20.0 * 10) * 20);
        }
        return new MobEffectInstance(mobEffect, duration, mobEffectInstance.getAmplifier());
    }
}