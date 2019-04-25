package net.seliba.pets.api

import net.seliba.pets.pets.PetType
import net.seliba.pets.dao.PetDao
import org.bukkit.entity.Player
import java.util.function.Consumer

class PetsApi(private val petDao: PetDao) {

    fun addPet(player: Player, petType: PetType) {
        petDao.addPet(player, petType)
    }

    fun getPets(player: Player): List<PetType> {
        return petDao.getPets(player)
    }

    fun hasPet(player: Player, petType: PetType): Boolean {
        return petDao.hasPet(player, petType)
    }

    fun removePet(player: Player, petType: PetType) {
        petDao.removePet(player, petType)
    }

    fun clearPets(player: Player) {
        getPets(player).forEach(Consumer {
            removePet(player, it)
        })
    }

}