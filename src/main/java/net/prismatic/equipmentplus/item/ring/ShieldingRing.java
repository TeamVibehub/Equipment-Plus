package net.prismatic.equipmentplus.item.ring;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.equipmentplus.api.status.PlayerShieldingStatus;

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