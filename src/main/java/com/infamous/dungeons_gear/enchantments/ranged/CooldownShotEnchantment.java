package com.infamous.dungeons_gear.enchantments.ranged;

import com.infamous.dungeons_gear.enchantments.ModEnchantmentTypes;
import com.infamous.dungeons_gear.enchantments.lists.RangedEnchantmentList;
import com.infamous.dungeons_gear.enchantments.types.DungeonsEnchantment;
import com.infamous.dungeons_libraries.items.artifacts.ArtifactItem;
import com.infamous.dungeons_libraries.utils.RangedAttackHelper;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.dungeons_gear.DungeonsGear.MODID;

import net.minecraft.world.item.enchantment.Enchantment.Rarity;

@Mod.EventBusSubscriber(modid= MODID)
public class CooldownShotEnchantment extends DungeonsEnchantment {

    public CooldownShotEnchantment() {
        super(Rarity.RARE, ModEnchantmentTypes.RANGED, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND});
    }

    public int getMaxLevel() {
        return 3;
    }

    @SubscribeEvent
    public static void onCooldownBowFired(ArrowLooseEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        int charge = event.getCharge();
        ItemStack stack = event.getBow();
        if(livingEntity instanceof Player && !event.getWorld().isClientSide){
            Player player = (Player) livingEntity;
            float arrowVelocity = RangedAttackHelper.getArrowVelocity(livingEntity, stack, charge);
            if(arrowVelocity >= 1.0F){
                int cooldownShotLevel = EnchantmentHelper.getItemEnchantmentLevel(RangedEnchantmentList.COOLDOWN_SHOT, stack);
                if(cooldownShotLevel > 0){
                    double cooldownReduction = 0.5 * cooldownShotLevel;
                    ArtifactItem.reduceArtifactCooldowns(player, cooldownReduction);
                }
            }
        }
    }
}
