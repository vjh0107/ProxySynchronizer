@file:UseSerializers(ItemStackSerializer::class)
package com.vjh0107.proxysynchronizer.inventory

import com.vjh0107.barcode.framework.database.player.data.PlayerData
import com.vjh0107.barcode.framework.serialization.serializers.ItemStackSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.bukkit.inventory.ItemStack

@Serializable
data class SyncPlayerInventoryData(
    val storage: List<ItemStack?> = listOf(),
    val armors: List<ItemStack?> = listOf(),
    val extras: List<ItemStack?> = listOf()
) : PlayerData