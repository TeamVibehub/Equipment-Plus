package net.prismatic.equipmentplus.core;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.util.Identifier;
import net.prismatic.equipmentplus.api.PlayerAbility;

public class EquipmentPlusAbilities {
    public static final ComponentType<PlayerAbility> RING =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("equipmentplus", "ring"), PlayerAbility.class);

}
