package net.prismatic.equipmentplus.api.backpack;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.Hand;

public class BackpackInventoryHandler implements Inventory, BackpackInventory {
    public DefaultedList<ItemStack> items;
    public int width;
    public int height;
    private final PlayerEntity player;

    public BackpackInventoryHandler(CompoundTag tag, PlayerEntity player)
    {
        this.player = player;

        this.fromTag(tag);
    }

    @Override
    public void clear()
    {
        this.items.clear();
    }

    @Override
    public int getInventoryWidth()
    {
        return width;
    }

    @Override
    public int getInventoryHeight()
    {
        return height;
    }

    @Override
    public int size()
    {
        return getInventoryWidth() * getInventoryHeight();
    }

    @Override
    public boolean isEmpty()
    {
        return this.items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot)
    {
        return items.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount)
    {
        return Inventories.splitStack(this.items, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot)
    {
        return Inventories.removeStack(this.items, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack)
    {
        this.items.set(slot, stack);
    }

    @Override
    public void markDirty()
    {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player)
    {
        return true;
    }

    public void fromTag(CompoundTag tag)
    {
        this.width = tag.contains("width") ? tag.getInt("width") : 9;
        this.height = tag.contains("height") ? tag.getInt("height") : 6;

        this.items = DefaultedList.ofSize(width * height, ItemStack.EMPTY);
        readItemsFromTag(this.items, tag);
    }

    public CompoundTag toTag()
    {
        CompoundTag tag = new CompoundTag();
        tag.putInt("width", width);
        tag.putInt("height", height);

        writeItemsToTag(this.items, tag);

        return tag;
    }

    @Override
    public void onOpen(PlayerEntity player)
    {
        Inventory.super.onOpen(player);
        player.playSound(SoundEvents.BLOCK_WOOL_BREAK, SoundCategory.PLAYERS, 1f, 1f);
    }

    @Override
    public void onClose(PlayerEntity player)
    {
        Inventory.super.onClose(player);

        if(!TrinketsApi.getTrinketComponent(player).getStack(SlotGroups.CHEST, Slots.BACKPACK).hasTag())
        {
            TrinketsApi.getTrinketComponent(player).getStack(SlotGroups.CHEST, Slots.BACKPACK).setTag(new CompoundTag());
        }

        TrinketsApi.getTrinketComponent(player).getStack(SlotGroups.CHEST, Slots.BACKPACK).getTag().put("backpack", toTag());
        player.playSound(SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.PLAYERS, 1f, 1f);
    }
}
