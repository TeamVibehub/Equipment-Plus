package net.prismatic.ringed.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.ringed.api.PlayerShieldingStatus;
import net.prismatic.ringed.api.Ring;

public class ShieldingRing extends Ring {

    public ShieldingRing() {
        super();
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