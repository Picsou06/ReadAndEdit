package fr.picsou.readandedit.components.listener.Player;

import fr.picsou.readandedit.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EditBookEvent implements Listener {

    @EventHandler
    public void OnPlayerEditBook(PlayerEditBookEvent event) {
        Player p = (Player) event.getPlayer();
        try {
            Main.getInstance().database.editbook(p.getUniqueId(),bookMetaToYaml(event.getNewBookMeta()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String bookMetaToYaml(BookMeta bookMeta) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
        dataOutput.writeObject(bookMeta);
        dataOutput.close();
        return outputStream.toString();
    }
}