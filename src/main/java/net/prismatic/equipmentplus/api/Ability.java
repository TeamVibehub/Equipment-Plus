package net.prismatic.equipmentplus.api;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import io.netty.buffer.Unpooled;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.LogManager;

public class Ability implements PlayerComponent, EntitySyncedComponent {
    private final PlayerEntity player;
    public int type;
    public boolean state;
    public StatusEffectInstance effect;
    public EntityAttributeModifier modifier;

    public Ability(PlayerEntity player) {
        this.player = player;
    }



    @Override
    public Entity getEntity() {
        return this.player;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.state = tag.getBoolean("state");
        this.type = tag.getInt("ability");
        if (tag.contains("effect")) {
            this.effect = StatusEffectInstance.fromTag(tag.getCompound("effect"));
        }
        if (tag.contains("modifier")) {
            this.modifier = EntityAttributeModifier.fromTag(tag.getCompound("modifier"));
        }
        this.sync();
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("state", this.state);
        tag.putInt("ability", this.type);
        if (this.effect != null) {
            tag.put("effect", this.effect.toTag(new CompoundTag()));
        }
        if (this.modifier != null) {
            tag.put("modifier", this.modifier.toTag());
        }
        return tag;
    }

    @Override
    public void syncWith(ServerPlayerEntity player) {
        if (player == this.player) {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            this.writeToPacket(buf);
            ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, PACKET_ID, buf);
        } else {
            LogManager.getLogger("PLACEHOLDER").warn(player.getName().asString() + " just tried to sync " + this.player.getName().asString() + "'s data to themselves!");
            return; // No sync for you, dirty hacker!
        }
    }

    @Override
    public void writeToPacket(PacketByteBuf packet) {
        packet.writeBoolean(this.state);
        packet.writeInt(this.type);

        if (this.effect != null) {
            packet.writeBoolean(true);
            packet.writeCompoundTag(this.effect.toTag(new CompoundTag()));
        } else {
            packet.writeBoolean(false);
        }

        if (this.modifier != null) {
            packet.writeBoolean(true);
            packet.writeCompoundTag(this.modifier.toTag());
        } else {
            packet.writeBoolean(false);
        }
    }

    @Override
    public void readFromPacket(PacketByteBuf packet) {
        this.state = packet.readBoolean();
        this.type = packet.readInt();

        if (packet.readBoolean()) {
            this.effect = StatusEffectInstance.fromTag(packet.readCompoundTag());
        }

        if (packet.readBoolean()) {
            this.modifier = EntityAttributeModifier.fromTag(packet.readCompoundTag());
        }
    }
}
