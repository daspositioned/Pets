package net.seliba.pets.listener

import net.seliba.pets.pets.PetSpawner
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener(private val petSpawner: PetSpawner): Listener {

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        petSpawner.despawnPet(event.player)
    }

}