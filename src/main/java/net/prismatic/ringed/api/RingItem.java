package net.prismatic.ringed.api;

import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.item.ItemGroup;


public class RingItem extends TrinketItem {

    public RingItem(ItemGroup group) {
        super(new Settings().group(group).maxCount(1));
    }

    public RingItem() {
        super(new Settings().maxCount(1));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(Slots.RING);
    }
}