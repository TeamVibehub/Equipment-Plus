package net.prismatic.ringed.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.ringed.api.PlayerShieldingStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public class ShieldingMixin {
    @ModifyVariable(at = @At("HEAD"), method = "damage")
    public float damage(float amount) {
        PlayerShieldingStatus status = new PlayerShieldingStatus((PlayerEntity) (Object) this);
        if (status.get()) {
            // 60% damage reduction, oh yeah
            amount = (amount*60) / 100;
        }
        return amount;
    }
}
