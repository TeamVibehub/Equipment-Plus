package net.prismatic.equipmentplus.api.status;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.equipmentplus.EquipmentPlusInitializer;
import net.prismatic.equipmentplus.api.component.LuckComponent;

public class PlayerLuckStatus {
    private final LuckComponent luck;

    public PlayerLuckStatus(PlayerEntity player) {
        this.luck = EquipmentPlusInitializer.LUCK.get(ComponentProvider.fromEntity(player));
    }

    public boolean get() {
        return luck.getState();
    }

    public void set(boolean state) {
        luck.setState(state);
    }
}
