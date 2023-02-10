package com.vjh0107.proxysynchronizer.enderchest

import com.vjh0107.barcode.framework.database.player.data.PlayerData
import org.bukkit.inventory.ItemStack

data class SyncPlayerEnderChestData(val contents: List<ItemStack?> = listOf()) : PlayerData