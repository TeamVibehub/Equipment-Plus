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
        int usedProtection;
        PlayerShieldingStatus status = new PlayerShieldingStatus((PlayerEntity) (Object) this);
        if (status.availableProtection() > 0) {
            if (status.availableProtection() > amount) {
                status.consume((int) amount);
                usedProtection = (int) amount;
                amount = 0;
            } else {
                status.consume(status.availableProtection());
                amount -= status.availableProtection();
                usedProtection = status.availableProtection();
            }
            status.setCooldown(usedProtection * 60);
        }
        return amount;
    }
}
