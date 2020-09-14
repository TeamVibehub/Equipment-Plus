package net.prismatic.ringed.component;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;

public interface RingComponent extends PlayerComponent {
    boolean getState();
    int getLevel();
    void setState(boolean state);
}
