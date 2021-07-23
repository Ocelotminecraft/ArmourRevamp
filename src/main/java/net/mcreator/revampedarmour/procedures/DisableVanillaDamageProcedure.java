package net.mcreator.revampedarmour.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.revampedarmour.RevampedArmourModElements;
import net.mcreator.revampedarmour.RevampedArmourMod;

import java.util.Map;
import java.util.HashMap;

@RevampedArmourModElements.ModElement.Tag
public class DisableVanillaDamageProcedure extends RevampedArmourModElements.ModElement {
	public DisableVanillaDamageProcedure(RevampedArmourModElements instance) {
		super(instance, 1);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				RevampedArmourMod.LOGGER.warn("Failed to load dependency entity for procedure DisableVanillaDamage!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			((PlayerEntity) entity).abilities.disableDamage = (true);
			((PlayerEntity) entity).sendPlayerAbilities();
		}
	}

	@SubscribeEvent
	public void onEntitySpawned(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		double i = entity.getPosX();
		double j = entity.getPosY();
		double k = entity.getPosZ();
		World world = event.getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
