package me.maximilian1021.utils;

import java.io.File;

public class FileManager {

    public static File getConf() {
        return new File("plugins/BungeeLobbyHub", "config.yml");
    }
}
