package net.prismatic.ringed.component;

import io.netty.buffer.Unpooled;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.prismatic.ringed.RingedInitializer;

public class ShieldingComponent implements RingComponent, EntitySyncedComponent {
    private boolean active;
    private final PlayerEntity player;

    public ShieldingComponent(PlayerEntity player) {
        this.player = player;
        this.active = false;
    }

    @Override
    public boolean getState() {
        return this.active;
    }

    @Override
    public void setState(boolean state) {
        this.active = state;
    }

    @Override
    public Entity getEntity() {
        return player;
    }

    @Override
    public ComponentType<?> getComponentType() {
        return RingedInitializer.SHIELDING;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.active = tag.getBoolean("state");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("state", this.active);
        return tag;
    }

    @Override
    public void syncWith(ServerPlayerEntity player) {
        if (player == this.player) {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            this.writeToPacket(buf);
            ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, PACKET_ID, buf);
        } else {
            return;
        }
    }
    @Override
    public void writeToPacket(PacketByteBuf packet) {
        packet.writeBoolean(this.active);
    }
    @Override
    public void readFromPacket(PacketByteBuf packet) {
        this.active = packet.readBoolean();
    }
}
