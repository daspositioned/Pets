package net.seliba.pets

import me.lucko.luckperms.LuckPerms
import net.seliba.pets.commands.PetCommand
import net.seliba.pets.dao.PetDao
import net.seliba.pets.listener.*
import net.seliba.pets.pets.PetSpawner
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.plugin.java.JavaPlugin

class Pets: JavaPlugin() {

    private var petDao: PetDao? = null
    private var petSpawner: PetSpawner? = null

    override fun onEnable() {
        Bukkit.getConsoleSender().sendMessage("§7--------------------------------------------")
        Bukkit.getConsoleSender().sendMessage("§aPets wurde gestartet!")
        Bukkit.getConsoleSender().sendMessage("§aDeveloper: §6Seliba §7(https://github.com/Seliba)")
        Bukkit.getConsoleSender().sendMessage("§aVersion: §6" + description.version)
        Bukkit.getConsoleSender().sendMessage("§7--------------------------------------------")

        petDao = PetDao(LuckPerms.getApi())
        petSpawner = PetSpawner(petDao!!, this)

        registerListener()
        registerCommands()

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, Runnable {
            petSpawner!!.pets.forEach(
                {
                    t, u -> u.teleport(t.location)
                    if(t.facing == BlockFace.EAST || t.facing == BlockFace.NORTH_EAST || t.facing == BlockFace.SOUTH_EAST) {
                        u.teleport(t.location.clone().subtract(1.0, 0.0, 0.0))
                    } else if(t.location.clone().add(1.0, 0.0, 0.0).block.type == Material.AIR) {
                        u.teleport(t.location.clone().add(1.0, 0.0, 0.0))
                    } else {
                        u.teleport(t.location)
                    }
                    if(u.passengers.contains(t!!)) {
                        u.velocity = t.location.direction
                    }
                }
            )
        }, 1, 1)
    }

    //t, u -> petSpawner!!.followPlayer(u as Creature, t, 1.0)

    override fun onDisable() {
        petSpawner!!.pets.forEach(
            {
                t, u -> u.remove()
            }
        )
        petSpawner!!.pets.clear()
    }

    private fun registerCommands() {
        getCommand("pets")!!.setExecutor(PetCommand(petDao!!))
    }

    private fun registerListener() {
        val pluginManager = Bukkit.getPluginManager()
        val asyncPlayerChatListener = AsyncPlayerChatListener(petDao!!)

        pluginManager.registerEvents(InventoryClickListener(petSpawner!!, petDao!!, asyncPlayerChatListener), this)
        pluginManager.registerEvents(EntityDamageListener(petSpawner!!), this)
        pluginManager.registerEvents(asyncPlayerChatListener, this)
        pluginManager.registerEvents(RightClickAtEntityListener(petSpawner!!), this)
        pluginManager.registerEvents(PlayerQuitListener(petSpawner!!), this)
    }

}