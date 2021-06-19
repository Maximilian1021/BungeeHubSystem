package me.maximilian1021.commands;

import me.maximilian1021.utils.FileManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class HubCommand extends Command {

  //private File LangFly = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
  private File getConfFile = FileManager.getConf();

    Configuration configuration;

    {
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(getConfFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public HubCommand() {
        super("Hub");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            ServerInfo Lobby = ProxyServer.getInstance().getServerInfo(configuration.getString("Haupt-Lobby"));
            ServerInfo LobbyFB = ProxyServer.getInstance().getServerInfo(configuration.getString("Fallback-Lobby"));
            String prefix = configuration.getString("Prefix");

                try {
                    //check ob Lobby online
                    Socket s = new Socket();
                    s.connect(new InetSocketAddress(configuration.getString("Adress-Hauptlobby"), (configuration.getInt("Port-Hauptlobby"))), 10);
                    s.close();
                    //PING SUCCESS Lobby
                    p.connect(Lobby);
                    p.sendMessage(prefix + " " +  configuration.getString("Move-to-Lobby"));

                } catch (Exception e) {
                    System.out.println(e);
                    //PING FAILED Lobby!
                    try {
                        Socket s = new Socket();
                        s.connect(new InetSocketAddress(configuration.getString("Adress-Fallback"), (configuration.getInt("Port-Fallback"))), 10);
                        s.close();
                        //PING SUCCESS Fallback
                        p.sendMessage(prefix + " " + configuration.getString( "Move-to-LobbyFB"));

                        p.connect(LobbyFB);
                    } catch (Exception e2) {
                        p.sendMessage(prefix + " " + configuration.getString("No-Lobby-Online"));
                        //PING FAILED Fallback
                    }
                }
            } else {
            sender.sendMessage(configuration.getString("No-Player"));
        }

        }

    }



