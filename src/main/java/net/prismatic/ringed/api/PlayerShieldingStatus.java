package net.prismatic.ringed.api;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.ringed.RingedInitializer;
import net.prismatic.ringed.component.ShieldingComponent;

public class PlayerShieldingStatus {
    private final ShieldingComponent shielding;

    PlayerShieldingStatus(PlayerEntity player) {
        this.shielding = RingedInitializer.SHIELDING.get(ComponentProvider.fromEntity(player));
    }

    public boolean get() {
        return shielding.getState();
    }

    public void set(boolean state) {
        shielding.setState(state);
    }

    public int level() {
        return shielding.getLevel();
    }
}
