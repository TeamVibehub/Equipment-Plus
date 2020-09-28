package net.prismatic.equipmentplus.api.ability;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class RingAbility implements Ability {
    private final PlayerEntity player;
    private int type;
    private boolean state;

    public RingAbility(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public boolean get(int type) {
        return this.type == type && this.state;
    }

    @Override
    public void set(boolean state, int type) {
        this.state = state;
        this.type = type;
        this.sync();
    }

    @Override
    public Entity getEntity() {
        return this.player;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.state = tag.getBoolean("state");
        this.type = tag.getInt("ability");
        this.sync();
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("state", this.state);
        tag.putInt("ability", this.type);
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
        packet.writeBoolean(this.state);
        packet.writeInt(this.type);
    }

    @Override
    public void readFromPacket(PacketByteBuf packet) {
        this.state = packet.readBoolean();
        this.type = packet.readInt();
    }
}
