package com.vjh0107.proxysynchronizer.core.service

import com.vjh0107.barcode.framework.database.player.data.PlayerData
import org.bukkit.entity.Player

interface SyncPlayerService<T : PlayerData> {
    fun loadSyncPlayerData(player: Player)

    fun loadSyncPlayerData(player: Player, data: T)

    fun saveSyncPlayerData(player: Player)
}