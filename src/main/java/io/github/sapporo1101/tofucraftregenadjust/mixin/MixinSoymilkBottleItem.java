package io.github.sapporo1101.tofucraftregenadjust.mixin;

import baguchan.tofucraft.item.SoymilkBottleItem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = SoymilkBottleItem.class, remap = false)
public class MixinSoymilkBottleItem {

    @ModifyArg(method = "lambda$finishUsingItem$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;m_7292_(Lnet/minecraft/world/effect/MobEffectInstance;)Z", ordinal = 2), index = 0)
    private MobEffectInstance modifyMobEffectInstance(MobEffectInstance mobEffectInstance) {
        final MobEffect mobEffect = mobEffectInstance.getEffect();
        int duration = mobEffectInstance.getDuration();
        if (mobEffect == MobEffects.REGENERATION) {
            duration = (int) (Math.log(duration / 20.0)/ Math.log(1.5) * 20);
        }
        return new MobEffectInstance(mobEffect, duration, mobEffectInstance.getAmplifier());
    }
}
