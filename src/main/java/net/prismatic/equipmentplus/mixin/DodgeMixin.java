package net.prismatic.equipmentplus.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.equipmentplus.api.status.PlayerDodgeStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public class DodgeMixin {
    @ModifyVariable(at = @At("HEAD"), method = "damage")
    public float damage(float amount, DamageSource source) {
        PlayerDodgeStatus status = new PlayerDodgeStatus((PlayerEntity) (Object) this);
        if (status.get() && !source.isUnblockable()) {
            double random = Math.random();
            if (random <= 0.1) {
                amount = 0;
            }
        }
        return amount;
    }
}