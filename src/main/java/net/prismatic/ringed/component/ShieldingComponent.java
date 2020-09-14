package net.prismatic.ringed.component;

import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.nbt.CompoundTag;
import net.prismatic.ringed.RingedInitializer;

public class ShieldingComponent implements RingComponent {
    private boolean active;
    private int level;

    public ShieldingComponent() {
        this.active = false;
        this.level = 0;
    }

    @Override
    public boolean getState() {
        return this.active;
    }

    @Override
    public int getLevel() {
        return this.level;
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
        this.level = tag.getInt("level");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("state", this.active);
        tag.putInt("level", this.level);
        return tag;
    }
}
