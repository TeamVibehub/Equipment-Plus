package net.prismatic.equipmentplus.api;

import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;
import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.equipmentplus.core.EquipmentPlusAbilities;
import org.apache.logging.log4j.LogManager;


public class Necklace extends TrinketItem {
    private final int type;
    private final StatusEffectInstance effect;
    private final EntityAttributeModifier modifier;

    public Necklace(int type) {
        super(new Settings().maxCount(1));
        this.type = type;
        this.effect = null;
        this.modifier = null;
    }

    public Necklace(StatusEffectInstance effect) {
        super(new Settings().maxCount(1));
        this.type = 0;
        this.effect = effect;
        this.modifier = null;
    }

    public Necklace(EntityAttributeModifier modifier) {
        super(new Settings().maxCount(1));
        this.type = 0;
        this.effect = null;
        this.modifier = modifier;
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        if (this.type <= 0 && this.effect == null && this.modifier == null) {
            LogManager.getLogger("EquipmentPlus").error("Someone fucked up! [Type of '" + this.getName().asString() + "' was <= 0 during construction]");
            EquipmentPlusAbilities.NECKLACE.get(ComponentProvider.fromEntity(player)).type = 0;
        }

        EquipmentPlusAbilities.NECKLACE.get(ComponentProvider.fromEntity(player)).state = true;

        if (this.type > 0) {
            EquipmentPlusAbilities.NECKLACE.get(ComponentProvider.fromEntity(player)).type = this.type;
        }

        if (this.modifier != null) {
            EquipmentPlusAbilities.NECKLACE.get(ComponentProvider.fromEntity(player)).modifier = this.modifier;
        }

        if (this.effect != null) {
            EquipmentPlusAbilities.NECKLACE.get(ComponentProvider.fromEntity(player)).effect = this.effect;
        }
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        EquipmentPlusAbilities.NECKLACE.get(ComponentProvider.fromEntity(player)).type = 0;
        EquipmentPlusAbilities.NECKLACE.get(ComponentProvider.fromEntity(player)).state = false;
        EquipmentPlusAbilities.NECKLACE.get(ComponentProvider.fromEntity(player)).modifier = null;
        EquipmentPlusAbilities.NECKLACE.get(ComponentProvider.fromEntity(player)).effect = null;
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(Slots.NECKLACE);
    }
}