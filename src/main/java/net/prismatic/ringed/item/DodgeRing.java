package net.prismatic.ringed.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.ringed.api.PlayerDodgeStatus;
import net.prismatic.ringed.api.Ring;

public class DodgeRing extends Ring {
    
    public DodgeRing() {
        super();
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        new PlayerDodgeStatus(player).set(true);
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        new PlayerDodgeStatus(player).set(false);
    }
}
