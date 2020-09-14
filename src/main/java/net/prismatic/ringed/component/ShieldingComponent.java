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
    private int cooldown;
    private int totalProtection;
    private int availableProtection;
    private final PlayerEntity player;

    public ShieldingComponent(PlayerEntity player) {
        this.player = player;
        this.active = false;
        this.cooldown = 0;
        this.totalProtection = 0;
        this.availableProtection = 0;
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
        this.availableProtection = tag.getInt("availableProtection");
        this.totalProtection = tag.getInt("totalProtection");
        this.cooldown = tag.getInt("cooldown");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("state", this.active);
        tag.putInt("availableProtection", this.availableProtection);
        tag.putInt("totalProtection", this.totalProtection);
        tag.putInt("cooldown", this.cooldown);
        return tag;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public void setCooldown(int amount) {
        this.cooldown = amount;
    }

    public void tick() {
        this.cooldown -= 1;
    }

    public int getTotalProtection() {
        return this.totalProtection;
    }

    public void setTotalProtection(int amount) {
        this.totalProtection = amount;
    }

    public int getAvailableProtection() {
        return this.availableProtection;
    }

    public void setAvailableProtection(int amount) {
        this.availableProtection = amount;
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
        packet.writeInt(this.availableProtection);
        packet.writeInt(this.totalProtection);
        packet.writeInt(this.cooldown);
    }

    @Override
    public void readFromPacket(PacketByteBuf packet) {
        this.active = packet.readBoolean();
        this.availableProtection = packet.readInt();
        this.totalProtection = packet.readInt();
        this.cooldown = packet.readInt();
    }
}
