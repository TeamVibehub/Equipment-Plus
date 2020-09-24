package net.prismatic.equipmentplus;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.prismatic.equipmentplus.item.ring.Ring;
import net.prismatic.equipmentplus.api.component.DodgeComponent;
import net.prismatic.equipmentplus.api.component.LuckComponent;
import net.prismatic.equipmentplus.api.component.ShieldingComponent;
import net.prismatic.equipmentplus.item.ring.DodgeRing;
import net.prismatic.equipmentplus.item.ring.LuckRing;
import net.prismatic.equipmentplus.item.ring.ShieldingRing;

public class EquipmentPlusInitializer implements ModInitializer {

    public static final ComponentType<ShieldingComponent> SHIELDING =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("equipmentplus", "shielding"), ShieldingComponent.class);

    public static final ComponentType<LuckComponent> LUCK =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("equipmentplus", "luck"), LuckComponent.class);

    public static final ComponentType<DodgeComponent> DODGE =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("equipmentplus", "dodge"), DodgeComponent.class);

    static Ring RING = new Ring();
    static LuckRing RING_LUCK = new LuckRing();
    static DodgeRing RING_DODGE = new DodgeRing();
    static ShieldingRing RING_SHIELDING = new ShieldingRing();
    public static ItemGroup RINGS = FabricItemGroupBuilder.create(
            new Identifier("equipmentplus", "rings"))
            .icon(() -> new ItemStack(RING))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(RING));
                stacks.add(new ItemStack(RING_LUCK));
                stacks.add(new ItemStack(RING_SHIELDING));
            })
            .build();

    @Override
    public void onInitialize() {
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(LUCK, new LuckComponent(playerEntity))));
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(DODGE, new DodgeComponent(playerEntity))));
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(SHIELDING, new ShieldingComponent(playerEntity))));
        EntityComponents.setRespawnCopyStrategy(LUCK, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(DODGE, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(SHIELDING, RespawnCopyStrategy.ALWAYS_COPY);
        TrinketSlots.addSlot(SlotGroups.HAND, Slots.RING, new Identifier("trinkets", "textures/item/empty_trinket_slot_ring.png"));
        Registry.register(Registry.ITEM, new Identifier("equipmentplus", "ring"), RING);
        Registry.register(Registry.ITEM, new Identifier("equipmentplus", "ring_luck"), RING_LUCK);
        Registry.register(Registry.ITEM, new Identifier("equipmentplus", "ring_dodge"), RING_DODGE);
        Registry.register(Registry.ITEM, new Identifier("equipmentplus", "ring_shielding"), RING_SHIELDING);
    }
}