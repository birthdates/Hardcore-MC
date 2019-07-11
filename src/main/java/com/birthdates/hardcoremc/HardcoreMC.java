package com.birthdates.hardcoremc;

import com.birthdates.hardcoremc.commands.DeathUnbanCommand;
import com.birthdates.hardcoremc.commands.DifficultyCommand;
import com.birthdates.hardcoremc.commands.HardcoreMCCommand;
import com.birthdates.hardcoremc.listeners.ConnectionListener;
import com.birthdates.hardcoremc.listeners.DeathListener;
import com.birthdates.hardcoremc.managers.ConfigurationManager;
import com.birthdates.hardcoremc.managers.DataManager;
import com.birthdates.hardcoremc.utils.Color;
import com.mongodb.client.MongoCollection;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HardcoreMC extends JavaPlugin {

    private static HardcoreMC instance;
    private ConfigurationManager configurationManager;
    private DataManager dataManager;
    private List<String> banned = new ArrayList<>();

    public static HardcoreMC getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        this.configurationManager = new ConfigurationManager(this);
        this.dataManager = new DataManager(configurationManager);
        loadData();
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);

        getCommand("hardcoremc").setExecutor(new HardcoreMCCommand());
        getCommand("difficulty").setExecutor(new DifficultyCommand());
        getCommand("deathunban").setExecutor(new DeathUnbanCommand());
        updateDifficulty();
    }


    public void updateDifficulty() {
        Bukkit.getWorlds().forEach(world -> world.setDifficulty(this.configurationManager.getDifficulty()));
    }

    public void onDisable() {
        saveData();
    }

    private void saveData() {
        MongoCollection<Document> data = dataManager.getPlayerData();
        Document newDoc = new Document();
        newDoc.put("banned", banned);
        Document first = data.find().first();
        if(first == null) {
            data.insertOne(newDoc);
        } else {
            data.replaceOne(first, newDoc);
        }
    }

    private void loadData() {
        MongoCollection<Document> data = dataManager.getPlayerData();
        Document listOfBanned = data.find().first();
        if(listOfBanned == null) return;
        banned = listOfBanned.getList("banned", String.class);
    }

    public void log(String msg) {
        Bukkit.getConsoleSender().sendMessage(Color.Translate(msg));
    }
}
