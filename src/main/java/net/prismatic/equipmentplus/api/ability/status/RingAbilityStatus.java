package net.prismatic.equipmentplus.api.ability.status;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.equipmentplus.core.EquipmentPlusAbilities;
import net.prismatic.equipmentplus.api.ability.RingAbility;

public class RingAbilityStatus {
    private final RingAbility ability;

    public RingAbilityStatus(PlayerEntity player) {
        this.ability = EquipmentPlusAbilities.RING.get(ComponentProvider.fromEntity(player));
    }

    public boolean get(int type) {
        return this.ability.get(type);
    }

    public void set(boolean state, int type) {
        ability.set(state, type);
    }
}
