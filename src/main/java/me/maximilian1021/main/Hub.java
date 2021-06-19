package me.maximilian1021.main;


import me.maximilian1021.commands.HubCommand;
import me.maximilian1021.utils.Metrics;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class Hub extends Plugin {



    @Override
    public void onEnable() {
        int pluginId = 11738;
        Metrics metrics = new Metrics(this, pluginId);
        // Plugin startup logic
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new HubCommand());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
