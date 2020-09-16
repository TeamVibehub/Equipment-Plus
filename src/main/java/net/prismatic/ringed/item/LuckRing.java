package net.prismatic.ringed.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.ringed.api.PlayerLuckStatus;
import net.prismatic.ringed.api.Ring;

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
