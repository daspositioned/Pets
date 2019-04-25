package net.seliba.pets.pets

import net.minecraft.server.v1_13_R2.EntityInsentient
import net.minecraft.server.v1_13_R2.PathEntity
import net.seliba.pets.Pets
import net.seliba.pets.dao.PetDao
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Mob
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.collections.HashMap
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftCreature
import org.bukkit.entity.Creature
import java.util.*


class PetSpawner(private val petDao: PetDao, private val javaPlugin: JavaPlugin) {

    val pets: MutableMap<Player, Entity> = HashMap()

    fun spawnPet(player: Player, petType: PetType) {
        val entity = player.world.spawnEntity(player.location, EntityType.valueOf(petType.name))
        val mob = entity as Mob

        mob.customName = "§6" + petDao.getPetName(player, petType)
        mob.isCustomNameVisible = true
        mob.isInvulnerable = true
        mob.setAI(false)

        pets.put(player, entity)
    }

    fun getPet(player: Player): PetType? {
        return PetType.valueOf(pets.getOrDefault(player, null)!!.type.name)
    }

    fun getPetAsEntity(player: Player): Entity? {
        return pets[player]
    }

    fun isUsingPet(player: Player): Boolean {
        return pets.containsKey(player)
    }

    fun despawnPet(player: Player) {
        if(!pets.containsKey(player)) {
            return
        }

        val pet: Entity = pets.getValue(player)

        pet.remove()
        pets.remove(player)
    }

}