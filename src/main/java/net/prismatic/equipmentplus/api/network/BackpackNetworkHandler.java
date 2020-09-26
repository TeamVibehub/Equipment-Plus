package net.prismatic.equipmentplus.api.network;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.prismatic.equipmentplus.api.item.Backpack;

@SuppressWarnings("deprecation")
public class BackpackNetworkHandler {
    public static void register() {
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
