package net.seliba.pets.dao

import me.lucko.luckperms.LuckPerms
import me.lucko.luckperms.api.LuckPermsApi
import me.lucko.luckperms.api.Node
import me.lucko.luckperms.api.NodeFactory
import me.lucko.luckperms.api.User
import net.seliba.pets.pets.PetType
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit
import java.util.function.Supplier

class PetDao(private val luckPermsApi: LuckPermsApi) {

    //private val luckPermsApi: LuckPermsApi
    private fun getUser(player: Player): User = luckPermsApi.userManager.loadUser(player.uniqueId)[1, TimeUnit.MINUTES]

    /*
    init {
        luckPermsApi = LuckPerms.getApi()
    }
     */

    fun addPet(player: Player, petType: PetType) {
        val user = getUser(player)
        user.setPermission(luckPermsApi.nodeFactory.newBuilder("pets." + petType.name).build())
        luckPermsApi.userManager.saveUser(user)
    }

    fun getPets(player: Player): List<PetType> {
        val pets = emptyList<PetType>()
        PetType.values().forEach {
            (
                if(player.hasPermission("pets." + it.name)) {
                    pets.plus(it)
                }
            )
        }
        return pets
    }

    fun hasPet(player: Player, petType: PetType): Boolean {
        return player.hasPermission("pets." + petType.name) || player.hasPermission("pets.admin")
    }

    fun removePet(player: Player, petType: PetType) {
        val user = getUser(player)
        user.unsetPermission(luckPermsApi.nodeFactory.newBuilder("pets." + petType.name).build())
        luckPermsApi.userManager.saveUser(user)
    }

    fun renamePet(player: Player, petType: PetType, newName: String) {
        val user = getUser(player)
        user.setPermission(luckPermsApi.nodeFactory.newBuilder("pets." + petType + "." + newName).build())
        luckPermsApi.userManager.saveUser(user)
    }

    fun getPetName(player: Player, petType: PetType): String {
        getUser(player).permissions.forEach(
            {
                if(it.permission.startsWith("pets." + petType + ".")) {
                    return it.permission.subSequence(it.permission.indexOf('.', 5, false) + 1, it.permission.length).toString()
                }
            }
        )
        return petType.getName()
    }

}