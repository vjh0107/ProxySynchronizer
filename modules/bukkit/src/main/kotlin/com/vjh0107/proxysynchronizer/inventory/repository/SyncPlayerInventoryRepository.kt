package com.vjh0107.proxysynchronizer.inventory.repository

import com.vjh0107.barcode.framework.database.player.PlayerIDWrapper
import com.vjh0107.proxysynchronizer.inventory.SyncPlayerInventoryData
import org.bukkit.entity.Player

interface SyncPlayerInventoryRepository {
    fun getPlayerInventoryData(id: PlayerIDWrapper): SyncPlayerInventoryData

    fun getPlayerInventoryData(player: Player): SyncPlayerInventoryData

    fun setPlayerInventoryData(id: PlayerIDWrapper, data: SyncPlayerInventoryData)

    fun setPlayerInventoryData(player: Player, data: SyncPlayerInventoryData)

    suspend fun noticeLoadPlayerInventoryData(player: Player)
}