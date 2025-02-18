package com.afunproject.dawncraft.integration.quests.custom.quests;

import java.util.Map;

import com.afunproject.dawncraft.Constants;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.AlchemistQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.BarrelQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.BellGhostQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.CultQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.IntroQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.KingQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.MaskGhostQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.MonsterSlayerQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.SageQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.SorcererQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.WerewolfQuest;
import com.afunproject.dawncraft.integration.quests.custom.quests.dc.WitchQuest;
import com.google.common.collect.Maps;

import net.minecraft.resources.ResourceLocation;

public class QuestsRegistry {

	private final static Map<ResourceLocation, Quest> QUESTS = Maps.newHashMap();

	static {
		registerDCQuests();
	}

	public static Quest registerQuest(ResourceLocation registry, Quest quest) {
		quest.setRegistryName(registry);
		QUESTS.put(registry, quest);
		return quest;
	}

	public static Quest getQuest(ResourceLocation registry) {
		return QUESTS.get(registry);
	}

	public static void registerDCQuests() {
		//intro
		registerQuest(Constants.loc("intro"), new IntroQuest());
		//patreon quests
		registerQuest(Constants.loc("werewolf"), new WerewolfQuest());
		registerQuest(Constants.loc("ghost"), new BellGhostQuest());
		registerQuest(Constants.loc("cult"), new CultQuest());
		registerQuest(Constants.loc("barrel"), new BarrelQuest());
		registerQuest(Constants.loc("alchemist"), new AlchemistQuest());
		registerQuest(Constants.loc("mask_ghost"), new MaskGhostQuest());
		registerQuest(Constants.loc("witch"), new WitchQuest());
		registerQuest(Constants.loc("king"), new KingQuest());
		registerQuest(Constants.loc("monster_slayer"), new MonsterSlayerQuest());
		registerQuest(Constants.loc("sage"), new SageQuest());
		registerQuest(Constants.loc("sorcerer"), new SorcererQuest());
	}

}
