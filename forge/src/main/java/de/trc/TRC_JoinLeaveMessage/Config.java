package de.trc.TRC_JoinLeaveMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.*;

public class Config {

    public static String JoinMessage = "§f[§2+§f] %player%";
    public static String LeaveMessage = "§f[§c-§f] %player%";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("config/TRC_JoinLeaveMessage.json");

    private static class ConfigData {
        String JoinMessage = "§f[§2+§f] %player%";
        String LeaveMessage = "§f[§c-§f] %player%";
    }

    public static void load() {
        try {
            if (Files.notExists(PATH)) {
                saveDefault();
                return;
            }

            Reader reader = Files.newBufferedReader(PATH);
            ConfigData data = GSON.fromJson(reader, ConfigData.class);
            reader.close();

            if (data != null) {
                JoinMessage = data.JoinMessage;
                LeaveMessage = data.LeaveMessage;
            }

        } catch (Exception e) {
            TRC_JoinLeaveMessage.LOGGER.warn("Config konnte nicht geladen werden, benutze Defaults.");
        }
    }

    public static void saveDefault() {
        try {
            Files.createDirectories(PATH.getParent());
            ConfigData data = new ConfigData();
            Writer writer = Files.newBufferedWriter(PATH);
            GSON.toJson(data, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
