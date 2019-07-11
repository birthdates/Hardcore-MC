package com.birthdates.hardcoremc.managers;

import com.birthdates.hardcoremc.HardcoreMC;
import com.birthdates.hardcoremc.utils.Color;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Difficulty;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
@Getter
public class ConfigurationManager {


    private File configFile;
    private YamlConfiguration configuration;
    private HardcoreMC plugin;
    @Setter
    private Difficulty difficulty;
    private String kickMessage;

    private String databaseIP,databaseUsername,databasePassword,databaseName;

    public ConfigurationManager(HardcoreMC plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "config.yml");
        this.tryDefaultConfig();
        this.loadSettings();
    }
    private void tryDefaultConfig()
    {
        if(!this.configFile.exists()) {
            this.plugin.saveResource("config.yml", false);
        }
        loadConfig();
    }

    private void loadConfig() {
        this.configuration = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public void saveConfig() {
       try {
           configuration.set("difficulty", difficulty.name().toUpperCase());
           configuration.save(configFile);
       } catch (Exception e) {

       }
    }

    private void loadSettings() {
        this.difficulty = Difficulty.valueOf(configuration.getString("difficulty"));
        this.kickMessage = Color.Translate(configuration.getString("kick-message"));

        this.databaseIP = configuration.getString("database.ip");
        this.databaseUsername = configuration.getString("database.username");
        this.databasePassword = configuration.getString("database.password");
        this.databaseName = configuration.getString("database.name");
    }

}
