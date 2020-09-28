package net.prismatic.equipmentplus.api.ability;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;

public interface Ability extends PlayerComponent, EntitySyncedComponent {
    boolean get(int type);
    void set(boolean state, int type);
}