package net.prismatic.equipmentplus.item.ring;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.equipmentplus.api.status.PlayerLuckStatus;

public class LuckRing extends Ring {

    public LuckRing() {
        super();
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        new PlayerLuckStatus(player).set(true);
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        new PlayerLuckStatus(player).set(false);
    }
}
