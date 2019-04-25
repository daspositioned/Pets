package net.seliba.pets.pets

import org.bukkit.Material
import org.bukkit.entity.EntityType

enum class PetType(private val entityName: String, private val entityType: EntityType, private val spawneggMaterial: Material) {

    CREEPER("Creeper", EntityType.CREEPER, Material.CREEPER_SPAWN_EGG),
    SKELETON("Skelett", EntityType.SKELETON, Material.SKELETON_SPAWN_EGG),
    SPIDER("Spinne", EntityType.SPIDER, Material.SPIDER_SPAWN_EGG),
    ZOMBIE("Zombie", EntityType.ZOMBIE, Material.ZOMBIE_SPAWN_EGG),
    SLIME("Schleim", EntityType.SLIME, Material.SLIME_SPAWN_EGG),
    PIG_ZOMBIE("Schweinezombie", EntityType.PIG_ZOMBIE, Material.ZOMBIE_PIGMAN_SPAWN_EGG),
    ENDERMAN("Enderman", EntityType.ENDERMAN, Material.ENDERMAN_SPAWN_EGG),
    CAVE_SPIDER("Höhlenspinne", EntityType.CAVE_SPIDER, Material.CAVE_SPIDER_SPAWN_EGG),
    SILVERFISH("Silberfisch", EntityType.SILVERFISH, Material.SILVERFISH_SPAWN_EGG),
    MAGMA_SLIME("Magma-Schleim", EntityType.MAGMA_CUBE, Material.MAGMA_CUBE_SPAWN_EGG),
    BAT("Fledermaus", EntityType.BAT, Material.MAGMA_CUBE_SPAWN_EGG),
    BLAZE("Lohe", EntityType.BLAZE, Material.BLAZE_SPAWN_EGG),
    PIG("Schwein", EntityType.PIG, Material.PIG_SPAWN_EGG),
    SHEEP("Schaf", EntityType.SHEEP, Material.SHEEP_SPAWN_EGG),
    COW("Kuh", EntityType.COW, Material.COW_SPAWN_EGG),
    CHICKEN("Huhn", EntityType.CHICKEN, Material.CHICKEN_SPAWN_EGG),
    SQUID("Tintenfisch", EntityType.SQUID, Material.SQUID_SPAWN_EGG),
    DOG("Hund", EntityType.WOLF, Material.WOLF_SPAWN_EGG),
    MUSHROOM_COW("Pilzkuh", EntityType.MUSHROOM_COW, Material.MOOSHROOM_SPAWN_EGG),
    OCELOT("Ozelot", EntityType.OCELOT, Material.OCELOT_SPAWN_EGG),
    HORSE("Pferd", EntityType.HORSE, Material.HORSE_SPAWN_EGG),
    RABBIT("Kaninchen", EntityType.RABBIT, Material.RABBIT_SPAWN_EGG),
    WITCH("Hexe", EntityType.WITCH, Material.WITCH_SPAWN_EGG),
    VILLAGER("Dorfbewohner", EntityType.VILLAGER, Material.VILLAGER_SPAWN_EGG);

    fun getName(): String {
        return entityName
    }

    fun getType(): EntityType {
        return entityType
    }

    fun getMaterial(): Material {
        return spawneggMaterial
    }

}