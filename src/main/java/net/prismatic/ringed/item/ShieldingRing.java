package net.prismatic.ringed.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.prismatic.ringed.api.PlayerShieldingStatus;
import net.prismatic.ringed.api.RingItem;

public class ShieldingRing extends RingItem {

    public ShieldingRing(ItemGroup group) {
        super(group);
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        new PlayerShieldingStatus(player).set(true);
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        new PlayerShieldingStatus(player).set(false);
    }
}