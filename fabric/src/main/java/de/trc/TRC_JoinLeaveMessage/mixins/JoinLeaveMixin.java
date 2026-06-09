package de.trc.TRC_JoinLeaveMessage.mixins;

import de.trc.TRC_JoinLeaveMessage.Config;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class JoinLeaveMixin {

    @Shadow @Final private MinecraftServer server;

    @Inject(method = "broadcast(Lnet/minecraft/text/Text;Z)V", at = @At("HEAD"), cancellable = true)
    private void hideVanilla(Text message, boolean overlay, CallbackInfo ci) {

        if (message.getContent() instanceof TranslatableTextContent translatable) {

            String key = translatable.getKey();

            if (key.equals("multiplayer.player.joined")
                    || key.equals("multiplayer.player.left")
                    || key.equals("multiplayer.player.joined.renamed")) {

                ci.cancel();
            }
        }
    }

    @Inject(method = "onPlayerConnect", at = @At("TAIL"))
    private void onJoin(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData data, CallbackInfo ci) {

        String name = player.getName().getString();
        String message = Config.JoinMessage;

        if (message.contains("%player%")) {
            message = message.replace("%player%", name);
        }

        broadcast(Text.literal(message));
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void onLeave(ServerPlayerEntity player, CallbackInfo ci) {

        String name = player.getName().getString();
        String message = Config.LeaveMessage;

        if (message.contains("%player%")) {
            message = message.replace("%player%", name);
        }

        broadcast(Text.literal(message));
    }

    @Unique
    private void broadcast(Text text) {
        if (server == null) return;
        server.getPlayerManager().broadcast(text, false);
    }
}
