package com.vjh0107.proxysynchronizer.core.repository

import com.vjh0107.barcode.framework.database.player.PlayerIDWrapper
import com.vjh0107.barcode.framework.database.player.data.PlayerData
import org.bukkit.entity.Player

interface SyncPlayerRepository<T : PlayerData> {
    fun getSyncPlayerData(id: PlayerIDWrapper): T

    fun getSyncPlayerData(player: Player): T

    fun setSyncPlayerData(id: PlayerIDWrapper, data: T)

    fun setSyncPlayerData(player: Player, data: T)
}