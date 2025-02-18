package com.afunproject.dawncraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.afunproject.dawncraft.capability.CapabilitiesRegister;
import com.afunproject.dawncraft.capability.SageQuestTracker;
import com.afunproject.dawncraft.effects.DawnCraftEffects;
import com.afunproject.dawncraft.integration.epicfight.EpicFightCompat;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;

@Mixin(Mob.class)
public abstract class MixinMob extends LivingEntity {

	public MixinMob(Level level) {
		super(null, level);
	}

	@Inject(at=@At("HEAD"), method = "isNoAi()Z", cancellable = true)
	public void isNoAi(CallbackInfoReturnable<Boolean> callback) {
		if (hasEffect(DawnCraftEffects.IMMOBILIZED.get())) {
			callback.setReturnValue(true);
			callback.cancel();
		}
	}

	@Inject(at=@At("HEAD"), method = "interact(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;", cancellable = true)
	public void interact(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> callback) {
		if (((LivingEntity)this) instanceof Animal) {
			if (ModList.get().isLoaded("epicfight")) if (EpicFightCompat.isCombatMode(player)) return;
			LazyOptional<SageQuestTracker> optional = player.getCapability(CapabilitiesRegister.SAGE_QUEST_TRACKER);
			if (optional.isPresent()) {
				SageQuestTracker cap = optional.resolve().get();
				if (cap.isActive()) cap.checkAnimal(player, (Animal)(LivingEntity)this);
			}
		}
	}

}
