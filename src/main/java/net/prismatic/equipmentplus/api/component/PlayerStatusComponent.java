package net.prismatic.equipmentplus.api.component;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;

public interface PlayerStatusComponent extends PlayerComponent {
    boolean getState();
    void setState(boolean state);
}
