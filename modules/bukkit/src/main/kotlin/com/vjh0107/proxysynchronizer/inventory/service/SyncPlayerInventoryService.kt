package com.vjh0107.proxysynchronizer.inventory.service

import com.vjh0107.proxysynchronizer.inventory.SyncPlayerInventoryData
import org.bukkit.entity.Player

interface SyncPlayerInventoryService {
    /**
     * SyncPlayerInventoryRepository 에서 데이터를 구한 후 플레이어의 인벤토리를 로드합니다.
     */
    fun loadPlayerInventory(player: Player)

    /**
     * 인자로 받은 data 를 통해 플레이어의 인벤토리를 로드합니다.
     */
    fun loadPlayerInventory(player: Player, data: SyncPlayerInventoryData)

    /**
     * 플레이어의 인벤토리를 SyncPlayerInventoryRepository 에 save 합니다.
     */
    fun savePlayerInventory(player: Player)
}