package com.afunproject.dawncraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.afunproject.dawncraft.effects.DawnCraftEffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

	public MixinLivingEntity(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
	}

	@Inject(at=@At("HEAD"), method = "getJumpPower()F", cancellable = true)
	protected void getJumpPower(CallbackInfoReturnable<Float> callback) {
		if (hasEffect(DawnCraftEffects.FROGFORM.get())) {
			callback.setReturnValue(0.77f * getBlockJumpFactor());
			callback.cancel();
			return;
		}
	}

	@Inject(at=@At("TAIL"), method = "calculateFallDamage(FF)I", cancellable = true)
	protected void calculateFallDamage(CallbackInfoReturnable<Integer> callback) {
		if (hasEffect(DawnCraftEffects.FROGFORM.get())) {
			callback.setReturnValue(callback.getReturnValue() - 5);
			callback.cancel();
			return;
		}
	}

	@Shadow
	protected abstract boolean hasEffect(MobEffect mobEffect);

}
