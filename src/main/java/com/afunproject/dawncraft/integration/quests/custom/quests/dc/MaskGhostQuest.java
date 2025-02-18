package com.afunproject.dawncraft.integration.quests.custom.quests.dc;

import com.afunproject.dawncraft.Constants;
import com.afunproject.dawncraft.dungeon.item.DungeonItems;
import com.afunproject.dawncraft.integration.quests.custom.entity.Fallen;
import com.afunproject.dawncraft.integration.quests.custom.quests.ItemTagQuest;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class MaskGhostQuest extends ItemTagQuest {

	public MaskGhostQuest() {
		super(Constants.loc("masks"), 4);
	}

	@Override
	protected void completeItemQuest(Player quest_completer, Mob entity, int phase, boolean accepted) {
		if (entity instanceof Fallen && phase == end_phase) {
			((Fallen)entity).startFading(new ItemStack(DungeonItems.CURSED_MASK.get()));
		}
	}

	@Override
	protected String getText() {
		return "text.dawncraft.quest.mask_ghost";
	}

}
