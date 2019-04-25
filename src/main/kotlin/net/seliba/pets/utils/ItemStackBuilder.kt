package net.seliba.pets.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class ItemStackBuilder(private val material: Material) {

    private val itemStack: ItemStack
    private val itemMeta: ItemMeta?

    init {
        itemStack = ItemStack(material)
        itemMeta = itemStack.itemMeta
    }

    fun setName(newName: String): ItemStackBuilder {
        itemMeta!!.setDisplayName(newName)
        return this
    }

    fun setLore(newLore: List<String>): ItemStackBuilder {
        itemMeta!!.lore = newLore
        return this
    }

    fun build(): ItemStack {
        itemStack.itemMeta = itemMeta!!
        return itemStack
    }

}