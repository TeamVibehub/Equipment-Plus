package net.prismatic.ringed.api;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.ringed.RingedInitializer;
import net.prismatic.ringed.component.ShieldingComponent;

public class PlayerShieldingStatus {
    private final ShieldingComponent shielding;
    private boolean cooldownCompleted;
    private int cooldownTicks;

    /**
     * Constructs a new PlayerShieldingStatus instance from a PlayerEntity
     * @param player The player to get the status of
     */
    public PlayerShieldingStatus(PlayerEntity player) {
        this.shielding = RingedInitializer.SHIELDING.get(ComponentProvider.fromEntity(player));
        this.cooldownTicks = 0;
        shielding.sync();
    }

    /**
     * Gets the state of the player's shielding
     * @return Whether or not the player has active shielding
     */
    public boolean get() {
        return shielding.getState();
    }

    /**
     * Sets the state of the player's shielding
     * @param state The state of the player's shielding
     */
    public void set(boolean state) {
        shielding.setState(state);
        shielding.sync();
    }
}
