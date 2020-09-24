package net.prismatic.equipmentplus.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.prismatic.equipmentplus.api.status.PlayerLuckStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ApplyBonusLootFunction.class)
public class LuckMixin {
    private LootContext context;

    @Inject(at = @At("HEAD"), method = "process")
    private void getLootContext(ItemStack stack, LootContext context, CallbackInfoReturnable<ItemStack> cir) {
        this.context = context;
    }
    @ModifyArg(index = 2, at = @At(value = "INVOKE", target = "Lnet/minecraft/loot/function/ApplyBonusLootFunction$Formula;getValue(Ljava/util/Random;II)I"), method = "process")
    private int addLuck(int level) {
        if (this.context.get(LootContextParameters.THIS_ENTITY) instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) this.context.get(LootContextParameters.THIS_ENTITY);
            if (new PlayerLuckStatus(player).get()) {
                level++;
            }
        }
        return level;
    }
}