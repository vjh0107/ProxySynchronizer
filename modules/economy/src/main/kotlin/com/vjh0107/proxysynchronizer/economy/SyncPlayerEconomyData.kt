package com.vjh0107.proxysynchronizer.economy

import com.vjh0107.barcode.framework.database.player.data.PlayerData
import kotlinx.serialization.Serializable

@Serializable
data class SyncPlayerEconomyData(
    val money: Double = 0.0
) : PlayerData