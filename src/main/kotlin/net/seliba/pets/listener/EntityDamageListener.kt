package net.seliba.pets.listener

import net.seliba.pets.pets.PetSpawner
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class EntityDamageListener(private val petSpawner: PetSpawner): Listener {

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        if(petSpawner.pets.containsValue(event.entity)) {
            event.isCancelled = true
        }
    }

}