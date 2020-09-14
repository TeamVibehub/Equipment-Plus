package net.prismatic.ringed.component;

import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.nbt.CompoundTag;
import net.prismatic.ringed.RingedInitializer;

public class ShieldingComponent implements RingComponent {
    private boolean active;
    private int cooldown;
    private int totalProtection;
    private int availableProtection;
    private int type;

    public ShieldingComponent() {
        this.active = false;
        this.cooldown = 0;
        this.totalProtection = 0;
        this.availableProtection = 0;
        this.type = 0;
    }

    @Override
    public boolean getState() {
        return this.active;
    }

    @Override
    public void setState(boolean state) {
        this.active = state;
    }

    @Override
    public ComponentType<?> getComponentType() {
        return RingedInitializer.SHIELDING;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.active = tag.getBoolean("state");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("state", this.active);
        return tag;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public void setCooldown(int amount) {
        this.cooldown = amount;
    }

    public void tick() {
        this.cooldown -= 1;
    }

    public int getTotalProtection() {
        return this.totalProtection;
    }

    public void setTotalProtection(int amount) {
        this.totalProtection = amount;
    }

    public int getAvailableProtection() {
        return this.availableProtection;
    }

    public void setAvailableProtection(int amount) {
        this.availableProtection = amount;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
