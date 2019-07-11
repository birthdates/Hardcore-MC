package com.birthdates.hardcoremc.managers;

import com.birthdates.hardcoremc.HardcoreMC;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import lombok.Getter;
import org.bson.Document;

public class DataManager {
    @Getter
    private MongoCollection<Document> playerData;

    public DataManager(ConfigurationManager config) {
        try {
            String connectionURI = "mongodb://";
            if(config.getDatabaseUsername() != null && !config.getDatabaseUsername().isEmpty()) {
                connectionURI += config.getDatabaseUsername() + ":" + config.getDatabasePassword() + "@";
            }
            connectionURI += config.getDatabaseIP();
            MongoClient client = new MongoClient(new MongoClientURI(connectionURI));
            playerData = client.getDatabase(config.getDatabaseName()).getCollection("playerData", Document.class);
        } catch(Exception e) {
            HardcoreMC.getInstance().log("&cFailed to load Mongo database: " + e.getLocalizedMessage());
        }
    }

}
