package net.mcreator.revampedarmour.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.world.Difficulty;

import net.mcreator.revampedarmour.RevampedArmourModVariables;
import net.mcreator.revampedarmour.RevampedArmourModElements;
import net.mcreator.revampedarmour.RevampedArmourMod;

import java.util.Map;
import java.util.HashMap;

@RevampedArmourModElements.ModElement.Tag
public class ReloadDefenceModifierProcedure extends RevampedArmourModElements.ModElement {
	public ReloadDefenceModifierProcedure(RevampedArmourModElements instance) {
		super(instance, 3);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				RevampedArmourMod.LOGGER.warn("Failed to load dependency world for procedure ReloadDefenceModifier!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if ((world.getDifficulty() == Difficulty.PEACEFUL)) {
			RevampedArmourModVariables.WorldVariables.get(world).WorldDefenceModifier = (double) 0.25;
			RevampedArmourModVariables.WorldVariables.get(world).syncData(world);
		} else if ((world.getDifficulty() == Difficulty.EASY)) {
			RevampedArmourModVariables.WorldVariables.get(world).WorldDefenceModifier = (double) 0.5;
			RevampedArmourModVariables.WorldVariables.get(world).syncData(world);
		} else if ((world.getDifficulty() == Difficulty.NORMAL)) {
			RevampedArmourModVariables.WorldVariables.get(world).WorldDefenceModifier = (double) 0.75;
			RevampedArmourModVariables.WorldVariables.get(world).syncData(world);
		} else if ((world.getDifficulty() == Difficulty.HARD)) {
			RevampedArmourModVariables.WorldVariables.get(world).WorldDefenceModifier = (double) 1;
			RevampedArmourModVariables.WorldVariables.get(world).syncData(world);
		}
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event) {
		IWorld world = event.getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("world", world);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
