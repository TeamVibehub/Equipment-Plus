package net.prismatic.equipmentplus.core;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.util.Identifier;
import net.prismatic.equipmentplus.api.Ability;

public class EquipmentPlusAbilities {
    public static final ComponentType<Ability> RING =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("equipmentplus", "ring"), Ability.class);

    public static final ComponentType<Ability> NECKLACE =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("equipmentplus", "necklace"), Ability.class);
}
