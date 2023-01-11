@file:UseSerializers(ItemStackSerializer::class)
package com.vjh0107.proxysynchronizer.inventory

import com.vjh0107.barcode.framework.database.player.data.AbstractSavablePlayerData
import com.vjh0107.barcode.framework.serialization.SerializableData
import com.vjh0107.barcode.framework.serialization.serializers.ItemStackSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.bukkit.inventory.ItemStack

@Serializable
data class SyncPlayerInventoryData(
    val inventory: MutableList<ItemStack>,
    val armors: MutableList<ItemStack>
) : AbstractSavablePlayerData(), SerializableData