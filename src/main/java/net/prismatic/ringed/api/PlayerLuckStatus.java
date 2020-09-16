package net.prismatic.ringed.api;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.ringed.RingedInitializer;
import net.prismatic.ringed.component.LuckComponent;

public class PlayerLuckStatus {
    private final LuckComponent luck;

    public PlayerLuckStatus(PlayerEntity player) {
        this.luck = RingedInitializer.LUCK.get(ComponentProvider.fromEntity(player));
        luck.sync();
    }

    public boolean get() {
        return luck.getState();
    }

    public void set(boolean state) {
        luck.setState(state);
        luck.sync();
    }
}
