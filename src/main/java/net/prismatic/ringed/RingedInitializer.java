package net.prismatic.ringed;

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
import net.prismatic.ringed.api.Ring;
import net.prismatic.ringed.component.DodgeComponent;
import net.prismatic.ringed.component.LuckComponent;
import net.prismatic.ringed.component.ShieldingComponent;
import net.prismatic.ringed.item.DodgeRing;
import net.prismatic.ringed.item.LuckRing;
import net.prismatic.ringed.item.ShieldingRing;

public class RingedInitializer implements ModInitializer {

    public static final ComponentType<ShieldingComponent> SHIELDING =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("ringed", "shielding"), ShieldingComponent.class);

    public static final ComponentType<LuckComponent> LUCK =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("ringed", "luck"), LuckComponent.class);

    public static final ComponentType<DodgeComponent> DODGE =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("ringed", "dodge"), DodgeComponent.class);

    static Ring RING = new Ring();
    static LuckRing RING_LUCK = new LuckRing();
    static DodgeRing RING_DODGE = new DodgeRing();
    static ShieldingRing RING_SHIELDING = new ShieldingRing();
    public static ItemGroup RINGED = FabricItemGroupBuilder.create(
            new Identifier("ringed", "ringed"))
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
        Registry.register(Registry.ITEM, new Identifier("ringed", "ring"), RING);
        Registry.register(Registry.ITEM, new Identifier("ringed", "ring_luck"), RING_LUCK);
        Registry.register(Registry.ITEM, new Identifier("ringed", "ring_dodge"), RING_DODGE);
        Registry.register(Registry.ITEM, new Identifier("ringed", "ring_shielding"), RING_SHIELDING);
    }
}