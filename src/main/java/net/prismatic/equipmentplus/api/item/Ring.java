package net.prismatic.equipmentplus.api.item;

import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.equipmentplus.api.ability.status.RingAbilityStatus;


public class Ring extends TrinketItem {
    private final int type;

    public Ring(int type) {
        super(new Settings().maxCount(1));
        this.type = type;
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        if (this.type > 0) {
            new RingAbilityStatus(player).set(true, this.type);
        }
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        new RingAbilityStatus(player).set(false, 0);
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(Slots.RING);
    }
}