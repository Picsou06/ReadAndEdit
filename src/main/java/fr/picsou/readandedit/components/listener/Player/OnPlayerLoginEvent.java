package fr.picsou.readandedit.components.listener.Player;

import fr.picsou.readandedit.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class OnPlayerLoginEvent implements Listener {

    @EventHandler
    public void OnPlayerConnection(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
        BookMeta bookMeta = null;
        try {
            bookMeta = yamlToBookMeta(Main.getInstance().database.getbook(p.getUniqueId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        book.setItemMeta(bookMeta);
        p.getInventory().setItem(1, book);
    }

    public BookMeta yamlToBookMeta(String yamlData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(yamlData.getBytes());
        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
        BookMeta bookMeta = (BookMeta) dataInput.readObject();
        dataInput.close();
        return bookMeta;
    }
}