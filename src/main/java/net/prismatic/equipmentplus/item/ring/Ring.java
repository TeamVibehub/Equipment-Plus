package net.prismatic.equipmentplus.item.ring;

import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;


public class Ring extends TrinketItem {

    public Ring() {
        super(new Settings().maxCount(1));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(Slots.RING);
    }
}