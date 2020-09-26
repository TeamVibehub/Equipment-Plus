package net.prismatic.equipmentplus;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.prismatic.equipmentplus.api.backpack.BackpackClientScreen;
import net.prismatic.equipmentplus.api.backpack.BackpackScreenHandler;
import org.lwjgl.glfw.GLFW;

@SuppressWarnings("deprecation")
public class EquipmentPlusClient implements ClientModInitializer {
    private boolean wasPressed;

    private static final KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "key.equipmentplus.backpack",
        GLFW.GLFW_KEY_B,
        "category.equipmentplus.equipmentplus"
    ));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            boolean isPressed = keyBinding.isPressed();
            if (isPressed && !wasPressed) {
                if (client.player == null) {
                    System.out.println("Client player is null?!");
                    return;
                }
                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                ClientSidePacketRegistry.INSTANCE.sendToServer(new Identifier("equipmentplus", "open_backpack"), buf);
            }
            wasPressed = isPressed;
        });

        ScreenProviderRegistry.INSTANCE.<BackpackScreenHandler>registerFactory(new Identifier("equipmentplus", "backpack_use"), (container -> new BackpackClientScreen(container, MinecraftClient.getInstance().player.inventory, new TranslatableText(Util.createTranslationKey("container", new Identifier("equipmentplus", "backpack"))))));
        ScreenProviderRegistry.INSTANCE.<BackpackScreenHandler>registerFactory(new Identifier("equipmentplus", "backpack_trinket"), (container -> new BackpackClientScreen(container, MinecraftClient.getInstance().player.inventory, new TranslatableText(Util.createTranslationKey("container", new Identifier("equipmentplus", "backpack"))))));
    }
}
