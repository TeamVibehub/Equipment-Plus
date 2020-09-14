package net.prismatic.ringed.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.prismatic.ringed.api.PlayerShieldingStatus;
import net.prismatic.ringed.api.RingItem;

public class ShieldingRing extends RingItem {
    private PlayerShieldingStatus status;

    public ShieldingRing(ItemGroup group) {
        super(group);
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        if (status == null) {
            this.status = new PlayerShieldingStatus(player);
        }
        status.set(true);
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        if (status == null) {
            this.status = new PlayerShieldingStatus(player);
        }
        status.set(false);
    }
}