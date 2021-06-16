package me.maximilian1021.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.net.InetSocketAddress;
import java.net.Socket;

public class HubCommand extends Command {


    public HubCommand() {
        super("Hub");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            ServerInfo si = ProxyServer.getInstance().getServerInfo("Lobby");

            try {
                //check ob Lobby online
                Socket s = new Socket();
                s.connect(new InetSocketAddress("localhost", 25567), 10);
                s.close();
                p.connect(si);
                p.sendMessage(TextComponent.fromLegacyText("test"));
                System.out.println("Test");
                //PING SUCCESS Lobby
            } catch (Exception e) {
                //PING FAILED Lobby!
                try {
                    Socket s = new Socket();
                    s.connect(new InetSocketAddress("localhost", 25568), 10);
                    s.close();
                    //PING SUCCESS Fallbach
                    p.connect(ProxyServer.getInstance().getServerInfo("LobbyFallback"));
                } catch (Exception e2) {
                    //PING FAILED Fallback
                }
            }


        } else {
            sender.sendMessage("Du musst ein Spieler sein um dies zu tun");
        }

    }

}

