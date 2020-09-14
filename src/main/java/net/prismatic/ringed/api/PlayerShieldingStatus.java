package net.prismatic.ringed.api;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.ringed.RingedInitializer;
import net.prismatic.ringed.component.ShieldingComponent;

public class PlayerShieldingStatus {
    private final ShieldingComponent shielding;

    /**
     * Constructs a new PlayerShieldingStatus instance from a PlayerEntity
     * @param player The player to get the status of
     */
    public PlayerShieldingStatus(PlayerEntity player) {
        this.shielding = RingedInitializer.SHIELDING.get(ComponentProvider.fromEntity(player));
        shielding.sync();
    }

    /**
     * Gets the state of the player's shielding.
     * Returns TRUE if the player has a Ring of Shielding equipped, FALSE otherwise.
     */
    public boolean get() {
        return shielding.getState();
    }

    /**
     * Sets the state of the player's shielding.
     */
    public void set(boolean state) {
        shielding.sync();
        shielding.setState(state);
        shielding.sync();
    }
}
