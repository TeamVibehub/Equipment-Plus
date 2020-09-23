package net.prismatic.ringed.api;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.ringed.RingedInitializer;
import net.prismatic.ringed.component.DodgeComponent;

public class PlayerDodgeStatus {
    private final DodgeComponent dodge;

    public PlayerDodgeStatus(PlayerEntity player) {
        this.dodge = RingedInitializer.DODGE.get(ComponentProvider.fromEntity(player));
        this.dodge.sync();
    }

    public boolean get() {
        return this.dodge.getState();
    }

    public void set(boolean state) {
        this.dodge.setState(state);
        this.dodge.sync();
    }
}
