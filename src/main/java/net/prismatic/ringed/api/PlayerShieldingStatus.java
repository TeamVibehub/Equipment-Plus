package net.prismatic.ringed.api;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.ringed.RingedInitializer;
import net.prismatic.ringed.component.ShieldingComponent;

public class PlayerShieldingStatus {
    private final ShieldingComponent shielding;
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

    /**
     * Sets the player's shielding cooldown
     * @param time The time to set the cooldown to, in ticks
     */
    public void setCooldown(int time) {
        shielding.setCooldown(time);
        shielding.sync();
        cooldownTicks = shielding.getCooldown();
    }

    /**
     * Ticks the cooldown by 1
     */
    public void tick() {
        cooldownTicks = shielding.getCooldown();
        if (shielding.getCooldown() == (cooldownTicks - 60) && shielding.getCooldown() >= 0) {
            shielding.setAvailableProtection(shielding.getAvailableProtection() + 1);
        }
        shielding.tick();
        shielding.sync();
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
        shielding.sync();
    }

    /**
     * Sets all protection to 0.
     */
    public void clearProtection() {
        shielding.setAvailableProtection(0);
        shielding.setTotalProtection(0);
        shielding.sync();
    }

    public void setProtection(int protecc) {
        shielding.setTotalProtection(protecc);
        if (shielding.getCooldown() == 0) {
            shielding.setAvailableProtection(protecc);
        }
    }
}
