package me.maximilian1021.main;


import me.maximilian1021.commands.HubCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class Hub extends Plugin {



    @Override
    public void onEnable() {
        // Plugin startup logic
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new HubCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
