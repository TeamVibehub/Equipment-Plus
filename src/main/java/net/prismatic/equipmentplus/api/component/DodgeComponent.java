package net.prismatic.equipmentplus.api.component;

import io.netty.buffer.Unpooled;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class DodgeComponent implements PlayerStatusComponent, EntitySyncedComponent {
    private final PlayerEntity player;
    private boolean state;

    public DodgeComponent(PlayerEntity player) {
        this.player = player;
        this.state = false;
    }

    @Override
    public boolean getState() {
        return this.state;
    }

    @Override
    public void setState(boolean state) {
        this.state = state;
        this.sync();
    }

    @Override
    public Entity getEntity() {
        return this.player;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.state = tag.getBoolean("state");
        this.sync();
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("state", this.state);
        return tag;
    }

    @Override
    public void syncWith(ServerPlayerEntity player) {
        if (player == this.player) {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            this.writeToPacket(buf);
            ServerSidePacketRegistry.INSTANCE.sendToPlayer(this.player, PACKET_ID, buf);
        } else {
            return;
        }
    }

    @Override
    public void writeToPacket(PacketByteBuf packet) {
        packet.writeBoolean(this.state);
    }

    @Override
    public void readFromPacket(PacketByteBuf packet) {
        this.state = packet.readBoolean();
    }
}
