package de.trc.TRCJoinLeaveMessage;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TRC_JoinLeaveMessage extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("TRC-Joinmessage Plugin aktiviert!");
    }

    @Override
    public void onDisable() {
        System.out.println("TRC-Joinmessage Plugin deaktiviert!");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String player = event.getPlayer().getName();
        String raw_message = getConfig().getString("join-message", "<red>Fehler beim Laden der Config von TRC-Joinmessage");

        if(raw_message.contains("%player%")) {
            raw_message = raw_message.replace("%player%", player);
        }
        Component message = MiniMessage.miniMessage().deserialize(raw_message);

        event.joinMessage(message);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        String player = event.getPlayer().getName();
        String raw_message = getConfig().getString("leave-message", "<red>Fehler beim Laden der Config von TRC-Joinmessage");

        if(raw_message.contains("%player%")) {
            raw_message = raw_message.replace("%player%", player);
        }
        Component message = MiniMessage.miniMessage().deserialize(raw_message);

        event.quitMessage(message);
    }
}
