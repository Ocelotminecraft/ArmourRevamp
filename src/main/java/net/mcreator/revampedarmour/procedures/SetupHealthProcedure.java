package net.mcreator.revampedarmour.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.revampedarmour.RevampedArmourModElements;
import net.mcreator.revampedarmour.RevampedArmourMod;

import java.util.Map;

@RevampedArmourModElements.ModElement.Tag
public class SetupHealthProcedure extends RevampedArmourModElements.ModElement {
	public SetupHealthProcedure(RevampedArmourModElements instance) {
		super(instance, 2);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				RevampedArmourMod.LOGGER.warn("Failed to load dependency entity for procedure SetupHealth!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("Health", ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getMaxHealth() : -1));
		entity.getPersistentData().putDouble("MaxHealth", ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getMaxHealth() : -1));
		entity.getPersistentData().putDouble("Defence", ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getTotalArmorValue() : 0));
	}
}
