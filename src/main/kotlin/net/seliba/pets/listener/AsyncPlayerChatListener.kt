package net.seliba.pets.listener

import net.seliba.pets.dao.PetDao
import net.seliba.pets.pets.PetType
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class AsyncPlayerChatListener(private val petDao: PetDao): Listener {

    private val renamePlayer: MutableMap<Player, PetType> = mutableMapOf()

    @EventHandler
    fun onAsyncChat(event: AsyncPlayerChatEvent) {
        val player = event.player
        if(renamePlayer.containsKey(player)) {
            event.isCancelled = true
            player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 5f, 5f)
            petDao.renamePet(player, renamePlayer[player]!!, event.message)
            player.sendMessage("§aDu hast dein Haustier erfolgreich umbenannt!")
            renamePlayer.remove(player)
        }
    }

    fun registerRename(player: Player, petType: PetType) {
        renamePlayer[player] = petType
    }


}