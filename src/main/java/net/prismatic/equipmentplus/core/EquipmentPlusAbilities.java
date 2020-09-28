package net.prismatic.equipmentplus.core;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.util.Identifier;
import net.prismatic.equipmentplus.api.ability.RingAbility;

public class EquipmentPlusAbilities {
    public static final ComponentType<RingAbility> RING =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("equipmentplus", "ring"), RingAbility.class);
}
