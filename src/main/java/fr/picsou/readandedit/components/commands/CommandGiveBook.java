package fr.picsou.readandedit.components.commands;

import fr.picsou.readandedit.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGiveBook implements CommandExecutor {
    FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.sendMessage("Coucou mon chou");
            player.getInventory().addItem(new ItemStack(Material.WRITABLE_BOOK));
        }
        return false;
    }
}
