package net.prismatic.equipmentplus.item.ring;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.equipmentplus.api.status.PlayerDodgeStatus;

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
