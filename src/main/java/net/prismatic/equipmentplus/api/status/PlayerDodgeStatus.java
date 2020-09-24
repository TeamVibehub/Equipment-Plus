package net.prismatic.equipmentplus.api.status;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.equipmentplus.EquipmentPlusInitializer;
import net.prismatic.equipmentplus.api.component.DodgeComponent;

public class PlayerDodgeStatus {
    private final DodgeComponent dodge;

    public PlayerDodgeStatus(PlayerEntity player) {
        this.dodge = EquipmentPlusInitializer.DODGE.get(ComponentProvider.fromEntity(player));
    }

    public boolean get() {
        return this.dodge.getState();
    }

    public void set(boolean state) {
        this.dodge.setState(state);
    }
}
