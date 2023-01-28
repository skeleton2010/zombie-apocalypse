package kr.co.skeleton.zombieapocalypse.packet;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_16_R3.Packet;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PListener {

    Channel channel;
    public static HashMap<UUID, Channel> channels = new HashMap<>();

    public void inject(Player p) {
        CraftPlayer cp  = (CraftPlayer) p;
        channel = cp.getHandle().playerConnection.networkManager.channel;
        channels.put(p.getUniqueId(), channel);

        if (channel.pipeline().get("PackInjector") != null) return;

        channel.pipeline().addAfter("decoder", "PackInjector", new MessageToMessageDecoder<Packet<?>>() {
            @Override
            protected void decode(ChannelHandlerContext chanenel, Packet<?> packet, List<Object> arg) throws Exception {
                arg.add(packet);
            }
        });
    }

    public void unInject(Player p) {
        channel = channels.get(p.getUniqueId());

        if (channel.pipeline().get("PacketInjector") != null) {
            channel.pipeline().remove("PacketInjector");
        }
    }

    public void readPacket(Packet<?> packet, Player p) {
        if (packet.getClass().getSimpleName().equalsIgnoreCase("")) {
            //command
        }
    }
}
