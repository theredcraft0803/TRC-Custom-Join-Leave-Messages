package de.trc.TRC_JoinLeaveMessage.event;

import de.trc.TRC_JoinLeaveMessage.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        MinecraftServer server = serverLevel.getServer();
        String name = player.getName().getString();
        String message = Config.JoinMessage.replace("%player%", name);

        server.getPlayerList().broadcastSystemMessage(Component.literal(message), false);
    }

    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        MinecraftServer server = serverLevel.getServer();
        String name = player.getName().getString();
        String message = Config.LeaveMessage.replace("%player%", name);

        server.getPlayerList().broadcastSystemMessage(Component.literal(message), false);
    }
}
