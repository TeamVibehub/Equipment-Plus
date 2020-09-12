package net.prismatic.ringed;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.prismatic.ringed.api.RingItem;

public class RingedInitializer implements ModInitializer {

    static RingItem RING = new RingItem();
    public static ItemGroup RINGED = FabricItemGroupBuilder.create(
            new Identifier("ringed", "ringed"))
            .icon(() -> new ItemStack(RING))
            .appendItems(stacks -> stacks.add(new ItemStack(RING)))
            .build();

    @Override
    public void onInitialize() {
        TrinketSlots.addSlot(SlotGroups.HAND, Slots.RING, new Identifier("trinkets", "textures/item/empty_trinket_slot_ring.png"));
        Registry.register(Registry.ITEM, new Identifier("ringed", "ring"), RING);
    }
}
