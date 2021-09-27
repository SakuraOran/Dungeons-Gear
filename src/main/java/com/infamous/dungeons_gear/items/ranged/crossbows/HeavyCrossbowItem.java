package com.infamous.dungeons_gear.items.ranged.crossbows;

import com.infamous.dungeons_gear.registry.ItemRegistry;
import com.infamous.dungeons_gear.utilties.DescriptionHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;

import net.minecraft.item.Item.Properties;

public class HeavyCrossbowItem extends AbstractDungeonsCrossbowItem {

    public HeavyCrossbowItem(Properties builder, int defaultChargeTimeIn, boolean isUniqueIn) {
        super(builder, defaultChargeTimeIn, isUniqueIn);
    }

    @Override
    public boolean shootsHeavyArrows(ItemStack stack) {
        return true;
    }

    @Override
    public boolean hasPunchBuiltIn(ItemStack stack) {
        return stack.getItem() == ItemRegistry.DOOM_CROSSBOW.get();
    }

    @Override
    public boolean hasRicochetBuiltIn(ItemStack stack) {
        return stack.getItem() == ItemRegistry.SLAYER_CROSSBOW.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.appendHoverText(stack, world, list, flag);
        DescriptionHelper.addFullDescription(list, stack);
    }
}
