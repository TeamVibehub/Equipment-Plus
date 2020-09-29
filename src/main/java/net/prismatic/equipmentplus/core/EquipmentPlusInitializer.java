package net.prismatic.equipmentplus.core;

import dev.emi.trinkets.api.*;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.prismatic.equipmentplus.api.Ability;
import net.prismatic.equipmentplus.ui.BackpackInventory;
import net.prismatic.equipmentplus.ui.BackpackScreenHandler;
import net.prismatic.equipmentplus.api.Backpack;
import net.prismatic.equipmentplus.api.Ring;

@SuppressWarnings("deprecation")
public class EquipmentPlusInitializer implements ModInitializer {

    static Ring RING_BASIC = new Ring(0);
    static Ring RING_LUCK = new Ring(1);
    static Ring RING_DODGE = new Ring(2);
    static Ring RING_SHIELDING = new Ring(3);
    static Backpack BACKPACK = new Backpack(new Item.Settings());
    static Backpack BACKPACK_NETHERITE = new Backpack(new Item.Settings().fireproof());
    public static ItemGroup RINGS = FabricItemGroupBuilder.create(
            new Identifier("equipmentplus", "rings"))
            .icon(() -> new ItemStack(RING_BASIC))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(RING_BASIC));
                stacks.add(new ItemStack(RING_LUCK));
                stacks.add(new ItemStack(RING_DODGE));
                stacks.add(new ItemStack(RING_SHIELDING));
            })
            .build();

    public static ItemGroup BACKPACKS = FabricItemGroupBuilder.create(
            new Identifier("equipmentplus", "backpacks"))
            .icon(() -> new ItemStack(BACKPACK))
            .appendItems(stacks ->{
                stacks.add(new ItemStack(BACKPACK));
                stacks.add(new ItemStack(BACKPACK_NETHERITE));
            })
            .build();


    @Override
    public void onInitialize() {
        ContainerProviderRegistry.INSTANCE.registerFactory(new Identifier("equipmentplus", "backpack_trinket"), ((syncId, identifier, player, packet) -> {
            final ItemStack stack = packet.readItemStack();
            final BackpackInventory inventory = Backpack.inventory(stack);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight());
        }));

        ContainerProviderRegistry.INSTANCE.registerFactory(new Identifier("equipmentplus", "backpack_use"), ((syncId, identifier, player, packet) -> {
            final ItemStack stack = packet.readItemStack();
            final Hand hand = packet.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventory inventory = Backpack.inventory(stack, hand);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight());
        }));
        
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(EquipmentPlusAbilities.RING, new Ability(playerEntity))));
        EntityComponents.setRespawnCopyStrategy(EquipmentPlusAbilities.RING, RespawnCopyStrategy.ALWAYS_COPY);

        Registry.register(Registry.ITEM, new Identifier("equipmentplus", "ring"), RING_BASIC);
        Registry.register(Registry.ITEM, new Identifier("equipmentplus", "backpack"), BACKPACK);
        Registry.register(Registry.ITEM, new Identifier("equipmentplus", "ring_luck"), RING_LUCK);
        Registry.register(Registry.ITEM, new Identifier("equipmentplus", "ring_dodge"), RING_DODGE);
        Registry.register(Registry.ITEM, new Identifier("equipmentplus", "ring_shielding"), RING_SHIELDING);
        Registry.register(Registry.ITEM, new Identifier("equipmentplus", "backpack_netherite"), BACKPACK_NETHERITE);
        TrinketSlots.addSlot(SlotGroups.HAND, Slots.RING, new Identifier("trinkets", "textures/item/empty_trinket_slot_ring.png"));
        TrinketSlots.addSlot(SlotGroups.CHEST, Slots.BACKPACK, new Identifier("trinkets", "textures/item/empty_trinket_slot_backpack.png"));

        ServerSidePacketRegistry.INSTANCE.register(new Identifier("equipmentplus", "open_backpack"), ((context, buf) -> context.getTaskQueue().execute(() -> {
            PlayerEntity player = context.getPlayer();
            TrinketComponent trinkets = TrinketsApi.getTrinketComponent(player);
            Item backpack = trinkets.getStack(SlotGroups.CHEST, Slots.BACKPACK).getItem();
            if (backpack instanceof Backpack) {
                ContainerProviderRegistry.INSTANCE.openContainer(new Identifier("equipmentplus", "backpack_trinket"), player, packet -> packet.writeItemStack(TrinketsApi.getTrinketComponent(player).getStack(SlotGroups.CHEST, Slots.BACKPACK)));
            }
        })));
    }
}