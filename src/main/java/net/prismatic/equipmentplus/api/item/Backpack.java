package net.prismatic.equipmentplus.api.item;

import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.prismatic.equipmentplus.api.backpack.BackpackInventoryHandler;

@SuppressWarnings("deprecation")
public class Backpack extends TrinketItem {
    public Backpack(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(Slots.BACKPACK);
    }

    public static BackpackInventoryHandler inventory(ItemStack stack) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundTag());
        }

        if (!stack.getTag().contains("backpack")) {
            stack.getTag().put("backpack", new CompoundTag());
        }


        return new BackpackInventoryHandler(stack.getTag().getCompound("backpack"));
    }

    public static BackpackInventoryHandler inventory(ItemStack stack, Hand hand) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundTag());
        }

        if (!stack.getTag().contains("backpack")) {
            stack.getTag().put("backpack", new CompoundTag());
        }


        return new BackpackInventoryHandler(stack.getTag().getCompound("backpack"), hand);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(new Identifier("equipmentplus", "backpack_use"), user, buf -> {
                buf.writeItemStack(user.getStackInHand(hand));
                buf.writeInt(hand == Hand.MAIN_HAND ? 0 : 1);
            });
        }

        return TypedActionResult.method_29237(user.getStackInHand(hand), world.isClient);
    }
}
