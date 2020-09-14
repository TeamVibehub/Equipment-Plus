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
    }

    /**
     * Gets the player's shielding cooldown
     * @return The player's shielding cooldown
     */
    public int getCooldown() {
        return shielding.getCooldown();
    }

    /**
     * Sets the player's shielding cooldown
     * @param time The time to set the cooldown to, in ticks
     */
    public void setCooldown(int time) {
        shielding.setCooldown(time);
    }

    /**
     * Ticks the cooldown by 1
     */
    public void tick() {
        shielding.tick();
    }

    /**
     * Gets the player's total shielding amount
     * @return How much shielding the player has
     */
    public int totalProtection() {
        return shielding.getTotalProtection();
    }

    /**
     * Gets the player's available shielding amount
     * @return How much shielding the player can use
     */
    public int availableProtection() {
        return shielding.getAvailableProtection();
    }

    /**
     * Uses shielding points
     * @param amount The amount of points to use
     */
    public void consume(int amount) {
        shielding.setAvailableProtection(shielding.getAvailableProtection() - amount);
    }

    /**
     * Gets the shielding type
     * Type 1: Normal, Type 2: Protective, Type 3: Speedy
     * @return The shielding type
     */
    public int type() {
        return shielding.getType();
    }

    /**
     * Sets the shielding type
     * @param type The type to use
     */
    public void type(int type) {
        shielding.setType(type);
    }

    /**
     * Sets all protection to 0.
     */
    public void clearProtection() {
        shielding.setAvailableProtection(0);
        shielding.setTotalProtection(0);
    }
}
