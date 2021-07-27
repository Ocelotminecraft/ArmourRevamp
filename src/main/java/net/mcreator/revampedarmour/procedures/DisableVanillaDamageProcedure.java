package net.mcreator.revampedarmour.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.revampedarmour.RevampedArmourModVariables;
import net.mcreator.revampedarmour.RevampedArmourModElements;
import net.mcreator.revampedarmour.RevampedArmourMod;

import java.util.Random;
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
		if (dependencies.get("amount") == null) {
			if (!dependencies.containsKey("amount"))
				RevampedArmourMod.LOGGER.warn("Failed to load dependency amount for procedure DisableVanillaDamage!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				RevampedArmourMod.LOGGER.warn("Failed to load dependency world for procedure DisableVanillaDamage!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double amount = dependencies.get("amount") instanceof Integer ? (int) dependencies.get("amount") : (double) dependencies.get("amount");
		IWorld world = (IWorld) dependencies.get("world");
		if (dependencies.get("event") != null) {
			Object _obj = dependencies.get("event");
			if (_obj instanceof Event) {
				Event _evt = (Event) _obj;
				if (_evt.isCancelable())
					_evt.setCanceled(true);
			}
		}
		if (((amount) <= 0)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).setHealth((float) ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1));
		} else {
			entity.getPersistentData().putDouble("Health", ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1));
			entity.getPersistentData().putDouble("Defence", ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getTotalArmorValue() : 0));
			if ((((amount) - Math.round(
					(((entity.getPersistentData().getDouble("Defence")) + (2 * (((EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION,
							((entity instanceof LivingEntity)
									? ((LivingEntity) entity)
											.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
									: ItemStack.EMPTY)))
							+ (EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(
													EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
											: ItemStack.EMPTY))))
							+ ((EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(
													EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
											: ItemStack.EMPTY)))
									+ (EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION,
											((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(
															EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
													: ItemStack.EMPTY)))))))
							* (RevampedArmourModVariables.WorldVariables.get(world).WorldDefenceModifier)))) <= 1)) {
				entity.getPersistentData().putDouble("Health", ((entity.getPersistentData().getDouble("Health")) - 1));
			} else {
				entity.getPersistentData().putDouble("Health", ((entity.getPersistentData().getDouble("Health")) - ((amount) - Math.round(
						(((entity.getPersistentData().getDouble("Defence")) + (2 * (((EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION,
								((entity instanceof LivingEntity)
										? ((LivingEntity) entity)
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
										: ItemStack.EMPTY)))
								+ (EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION,
										((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getItemStackFromSlot(
														EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
												: ItemStack.EMPTY))))
								+ ((EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION,
										((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getItemStackFromSlot(
														EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
												: ItemStack.EMPTY)))
										+ (EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION,
												((entity instanceof LivingEntity)
														? ((LivingEntity) entity).getItemStackFromSlot(
																EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
														: ItemStack.EMPTY)))))))
								* (RevampedArmourModVariables.WorldVariables.get(world).WorldDefenceModifier))))));
			}
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).setHealth((float) (entity.getPersistentData().getDouble("Health")));
			if ((((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
					? ((ServerPlayerEntity) entity).getAdvancements()
							.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
									.getAdvancement(new ResourceLocation("minecraft:story/root")))
							.isDone()
					: false)) {
				{
					double _setval = (double) (entity.getPersistentData().getDouble("Health"));
					entity.getCapability(RevampedArmourModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.PlayerHealth = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).setHealth((float) ((entity.getCapability(RevampedArmourModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new RevampedArmourModVariables.PlayerVariables())).PlayerHealth));
			}
			{
				ItemStack _ist = ((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
						: ItemStack.EMPTY);
				if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
					_ist.shrink(1);
					_ist.setDamage(0);
				}
			}
			{
				ItemStack _ist = ((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
						: ItemStack.EMPTY);
				if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
					_ist.shrink(1);
					_ist.setDamage(0);
				}
			}
			{
				ItemStack _ist = ((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
						: ItemStack.EMPTY);
				if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
					_ist.shrink(1);
					_ist.setDamage(0);
				}
			}
			{
				ItemStack _ist = ((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
						: ItemStack.EMPTY);
				if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
					_ist.shrink(1);
					_ist.setDamage(0);
				}
			}
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((new java.text.DecimalFormat("##.##")
					.format(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getTotalArmorValue() : 0)))), (false));
		}
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			Entity sourceentity = event.getSource().getTrueSource();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			double amount = event.getAmount();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("amount", amount);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
