package net.seliba.pets.inventory

import net.seliba.pets.pets.PetType
import net.seliba.pets.api.PetsApi
import net.seliba.pets.dao.PetDao
import net.seliba.pets.utils.ItemStackBuilder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class PetInventory(private val petDao: PetDao) {

    fun openPetInventory(player: Player) {
        val petInventory: Inventory = Bukkit.createInventory(null, 3 * 9, "§6Haustiere")
        val petsApi = PetsApi(petDao)

        PetType.values().forEach {
            val itemStackBuilder = ItemStackBuilder(it.getMaterial())
            if(petsApi.hasPet(player, it)) {
                itemStackBuilder.setName("§a" + petDao.getPetName(player, it))
            } else {
                itemStackBuilder.setName("§c" + it.getName())
            }
            petInventory.addItem(itemStackBuilder.build())
        }

        player.openInventory(petInventory)
    }

    fun openRenameInventory(player: Player) {
        val inventory = Bukkit.createInventory(null, 3 * 9, "§6Haustier umbenennen")
        val petsApi = PetsApi(petDao)

        PetType.values().forEach {
            if(petsApi.hasPet(player, it)) {
                inventory.addItem(ItemStackBuilder(it.getMaterial())
                    .setName("§a" + petDao.getPetName(player, it)).build())
            }
        }

        player.openInventory(inventory)
    }

}