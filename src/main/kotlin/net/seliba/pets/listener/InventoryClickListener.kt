package net.seliba.pets.listener

import net.seliba.pets.dao.PetDao
import net.seliba.pets.pets.PetSpawner
import net.seliba.pets.pets.PetType
import net.seliba.pets.utils.ItemStackBuilder
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryClickListener(private val petSpawner: PetSpawner, private val petDao: PetDao, private val chatListener: AsyncPlayerChatListener): Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player: Player = event.whoClicked as Player

        if(event.clickedInventory == null || event.currentItem == null) {
            return
        }

        if(event.view.title == "§6Haustiere") {
            event.isCancelled = true
            val petType = getTypeByMaterial(event.currentItem!!.type)!!
            if(!petDao.hasPet(player, petType)) {
                return
            }
            if(petSpawner.isUsingPet(player)) {
                petSpawner.despawnPet(player)
                player.sendMessage("§aDu hast §6" + petDao.getPetName(player, petSpawner.getPet(player)!!) + " §aweggeschickt!")
            } else {
                petSpawner.spawnPet(player, petType)
                player.sendMessage("§aDu hast §6" + petDao.getPetName(player, petType) + " §azu dir gerufen!")
            }
            player.closeInventory()
        } else if(event.view.title == "§6Haustier umbenennen") {
            event.isCancelled = true
            if(!event.currentItem!!.itemMeta!!.displayName.contains("§c")) {
                player.closeInventory()
                chatListener.registerRename(player, getTypeByMaterial(event.currentItem!!.type)!!)
                player.sendMessage("§aBitte gebe den neuen Namen vom Haustier in den Chat ein!")
                player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 5f, 5f)
            }
        }
    }

    private fun getTypeByMaterial(material: Material): PetType? {
        PetType.values().forEach {
            if(it.getMaterial() == material) {
                return it
            }
        }
        return null
    }

}