package net.prismatic.equipmentplus.api.item;

import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.prismatic.equipmentplus.api.backpack.BackpackInventoryHandler;

@SuppressWarnings("deprecation")
public class Backpack extends TrinketItem {
    public Backpack() {
        super(new Settings().maxCount(1));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(Slots.BACKPACK);
    }

    public static BackpackInventoryHandler inventory(ItemStack stack, PlayerEntity player) {
        if(!stack.hasTag()) {
            stack.setTag(new CompoundTag());
        }

        if(!stack.getTag().contains("backpack")) {
            stack.getTag().put("backpack", new CompoundTag());
        }

        return new BackpackInventoryHandler(stack.getTag().getCompound("backpack"), player);
    }
}
