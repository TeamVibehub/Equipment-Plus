package net.prismatic.ringed.api;

import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.item.ItemGroup;
import net.prismatic.ringed.RingedInitializer;


public class RingItem extends TrinketItem {

    public RingItem() {
        super(new Settings().group(RingedInitializer.RINGED).maxCount(1));
    }

    public RingItem(ItemGroup group) {
        super(new Settings().group(group).maxCount(1));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(Slots.RING);
    }
}