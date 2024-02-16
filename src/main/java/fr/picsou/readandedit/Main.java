package fr.picsou.readandedit;

import fr.picsou.readandedit.components.commands.CommandGiveBook;
import fr.picsou.readandedit.components.listener.ListenerManager;
import fr.picsou.readandedit.mysql.DatabaseManager;
import fr.picsou.readandedit.utils.Commands.SimpleCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_20_R2.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;
    FileConfiguration config = getConfig();
    public DatabaseManager database;

    @Override
    public void onEnable(){
        instance = this;
        config.options().copyDefaults(true);
        saveConfig();
        database = new DatabaseManager(config.getString("Database.Host"), config.getInt("Database.Port"), config.getString("Database.Name"), config.getString("Database.ID"), config.getString("Database.MDP"));
        database.connection();
        createCommand(new SimpleCommand("givebook", "", new CommandGiveBook()));
        new ListenerManager(this);
    }

    @Override
    public void onDisable() {
        System.out.println("[ReadAndEdit] OFF");
    }
    private void createCommand(SimpleCommand command) {
        CraftServer server = (CraftServer) getServer();
        server.getCommandMap().register(getName(), command);
    }

    public static Main getInstance() {
        return instance;
    }
    }

