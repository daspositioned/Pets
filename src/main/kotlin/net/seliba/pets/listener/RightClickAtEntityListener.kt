package net.seliba.pets.listener

import net.seliba.pets.pets.PetSpawner
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent

class RightClickAtEntityListener(private val petSpawner: PetSpawner): Listener {

    @EventHandler
    fun onRightClick(event: PlayerInteractAtEntityEvent) {
        val player = event.player
        val entity = event.rightClicked

        if(!petSpawner.isUsingPet(player)) {
            return
        }

        if(petSpawner.getPetAsEntity(player)!!.entityId == entity.entityId) {
            entity.addPassenger(player)
        }
    }

}