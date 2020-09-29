package net.prismatic.equipmentplus.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.equipmentplus.api.PlayerAbility;
import net.prismatic.equipmentplus.core.EquipmentPlusAbilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public class DodgeMixin {
    @ModifyVariable(at = @At("HEAD"), method = "damage")
    public float damage(float amount, DamageSource source) {
        PlayerAbility ability = EquipmentPlusAbilities.RING.get(((PlayerEntity) (Object) this));
        if (ability.get(1) && !source.isUnblockable()) {
            double random = Math.random();
            if (random <= 0.1) {
                amount = 0;
            }
        }
        return amount;
    }
}