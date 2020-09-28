package net.prismatic.equipmentplus.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.equipmentplus.api.ability.status.RingAbilityStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public class DodgeMixin {
    @ModifyVariable(at = @At("HEAD"), method = "damage")
    public float damage(float amount, DamageSource source) {
        RingAbilityStatus status = new RingAbilityStatus((PlayerEntity) (Object) this);
        if (status.get(1) && !source.isUnblockable()) {
            double random = Math.random();
            if (random <= 0.1) {
                amount = 0;
            }
        }
        return amount;
    }
}