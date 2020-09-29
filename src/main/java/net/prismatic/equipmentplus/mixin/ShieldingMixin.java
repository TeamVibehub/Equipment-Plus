package net.prismatic.equipmentplus.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.equipmentplus.api.Ability;
import net.prismatic.equipmentplus.core.EquipmentPlusAbilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public class ShieldingMixin {
    @ModifyVariable(at = @At("HEAD"), method = "damage")
    public float damage(float amount, DamageSource source) {
        Ability ability = EquipmentPlusAbilities.RING.get(((PlayerEntity) (Object) this));
        if (ability.get(3) && !source.isUnblockable()) {
            // 60% damage reduction, oh yeah
            amount = (amount*60) / 100;
        }
        return amount;
    }
}
