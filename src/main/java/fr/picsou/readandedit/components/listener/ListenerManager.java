package fr.picsou.readandedit.components.listener;

import fr.picsou.readandedit.components.listener.Player.EditBookEvent;
import fr.picsou.readandedit.components.listener.Player.OnPlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManager {

    public ListenerManager(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new EditBookEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnPlayerLoginEvent(), plugin);
    }
}
