package net.prismatic.ringed.api;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;

public interface RingComponent extends PlayerComponent {
    boolean getState();
    void setState(boolean state);
}
