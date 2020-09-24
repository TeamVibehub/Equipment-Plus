package net.prismatic.equipmentplus.api.status;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.equipmentplus.EquipmentPlusInitializer;
import net.prismatic.equipmentplus.api.component.ShieldingComponent;

public class PlayerShieldingStatus {
    private final ShieldingComponent shielding;

    public PlayerShieldingStatus(PlayerEntity player) {
        this.shielding = EquipmentPlusInitializer.SHIELDING.get(ComponentProvider.fromEntity(player));
    }

    public boolean get() {
        return shielding.getState();
    }

    public void set(boolean state) {
        shielding.setState(state);
    }
}