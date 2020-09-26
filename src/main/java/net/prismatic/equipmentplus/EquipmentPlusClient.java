package net.prismatic.equipmentplus;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.prismatic.equipmentplus.api.backpack.BackpackClientScreen;
import net.prismatic.equipmentplus.api.backpack.BackpackScreenHandler;
import net.prismatic.equipmentplus.api.item.Backpack;
import org.lwjgl.glfw.GLFW;

@SuppressWarnings("deprecation")
public class EquipmentPlusClient implements ClientModInitializer {

    private static final KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "key.equipmentplus.backpack",
        GLFW.GLFW_KEY_B,
        "category.equipmentplus.equipmentplus"
    ));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.isPressed() && !keyBinding.wasPressed()) {
                if (client.player == null) {
                    System.out.println("Client player is null?!");
                    return;
                }
                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                ClientSidePacketRegistry.INSTANCE.sendToServer(new Identifier("equipmentplus", "open_backpack"), buf);
            }
        });

        ScreenProviderRegistry.INSTANCE.<BackpackScreenHandler>registerFactory(new Identifier("equipmentplus", "backpack"), (container -> new BackpackClientScreen(container, MinecraftClient.getInstance().player.inventory, new TranslatableText(Util.createTranslationKey("container", new Identifier("equipmentplus", "backpack"))))));
    }
}
