package net.seliba.pets.commands

import net.seliba.pets.api.PetsApi
import net.seliba.pets.dao.PetDao
import net.seliba.pets.inventory.PetInventory
import net.seliba.pets.listener.AsyncPlayerChatListener
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class PetCommand(private val petDao: PetDao): CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(args.size != 2 && args.isNotEmpty() && args.size != 1) {
            sendHelpMessage(sender)
            return false
        }

        if(args.isEmpty() && sender is Player) {
            PetInventory(petDao).openPetInventory(player = sender)
            return true
        }

        if(args[0].equals("rename", true) && sender is Player) {
            PetInventory(petDao).openRenameInventory(player = sender)
            return true
        }

        if(!args[0].equals("clear", true)) {
            sendHelpMessage(sender)
            return false
        }

        if(args[0].equals("rename", true) && sender is Player) {
            PetInventory(petDao).openRenameInventory(player = sender)
            return true
        }

        if(sender.hasPermission("pets.admin")) {
            sender.sendMessage("§cDazu hast du keine Rechte!")
            return false
        }

        if (Bukkit.getPlayer(args[1]) == null) {
            sender.sendMessage("§cDieser Spieler ist nicht online!")
            return false
        }

        PetsApi(petDao).clearPets(Bukkit.getPlayer(args[1])!!)
        return true
    }

    private fun sendHelpMessage(sender: CommandSender) {
        sender.sendMessage("§7-----------------------------------------------------")
        sender.sendMessage("§7Commands:")
        sender.sendMessage("§6/pets                     §7| §aZeigt alle Haustiere an")
        sender.sendMessage("§6/pets rename              §7| §aBenennt ein Haustier um")
        if(sender.hasPermission("pets.admin")) {
            sender.sendMessage("§6/pets clear <Spieler> §7| §aEntfernt alle Pets des Spielers")
        }
        sender.sendMessage("§7-----------------------------------------------------")
    }

}