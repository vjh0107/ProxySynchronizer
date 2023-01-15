package com.vjh0107.proxysynchronizer.inventory.repository.impl

import com.vjh0107.barcode.framework.AbstractBarcodePlugin
import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.database.player.PlayerIDWrapper
import com.vjh0107.barcode.framework.database.player.getPlayerID
import com.vjh0107.barcode.framework.koin.annotation.BarcodeSingleton
import com.vjh0107.barcode.framework.serialization.deserialize
import com.vjh0107.barcode.framework.serialization.serialize
import com.vjh0107.proxysynchronizer.ProxySavablePlayerDataRepository
import com.vjh0107.proxysynchronizer.inventory.SyncPlayerInventoryData
import com.vjh0107.proxysynchronizer.inventory.repository.SyncPlayerInventoryRepository
import com.vjh0107.proxysynchronizer.inventory.entity.PlayerInventoryDataTable
import com.vjh0107.proxysynchronizer.inventory.entity.PlayerInventoryEntity
import org.bukkit.entity.Player
import java.time.LocalDateTime

@BarcodeComponent
@BarcodeSingleton(binds = [SyncPlayerInventoryRepository::class])
class SyncPlayerInventoryRepositoryImpl(
    plugin: AbstractBarcodePlugin
) : ProxySavablePlayerDataRepository<SyncPlayerInventoryData>(plugin), SyncPlayerInventoryRepository {
    override fun getTablesToLoad() = listOf(PlayerInventoryDataTable)

    override suspend fun loadDataProxySafely(id: PlayerIDWrapper): SyncPlayerInventoryData {
        return dataSource.query {
            val result = PlayerInventoryEntity.findByProfileID(id.profileID) ?: PlayerInventoryEntity.new(id) {}
            result.inventory.deserialize()
        }
    }

    override suspend fun saveData(id: PlayerIDWrapper, playerData: SyncPlayerInventoryData) {
        dataSource.query {
            with(PlayerInventoryEntity.findByProfileID(id.profileID)) {
                if (this == null) {
                    throw NullPointerException("profileID ${id.profileID} 를 통해 플레이어를 찾을 수 없습니다. 데이터 저장을 건너뜁니다.")
                }
                this.inventory = playerData.serialize()
                this.updatedAt = LocalDateTime.now()
            }
        }
    }

    override fun getPlayerInventoryData(id: PlayerIDWrapper): SyncPlayerInventoryData {
        return getPlayerData(id) ?: throw NullPointerException("id $id 를 통해 플레이어 인벤토리를 찾을 수 없습니다.")
    }

    override fun getPlayerInventoryData(player: Player): SyncPlayerInventoryData {
        return getPlayerInventoryData(player.getPlayerID())
    }

    override fun setPlayerInventoryData(id: PlayerIDWrapper, data: SyncPlayerInventoryData) {
        dataMap[id] = data
    }

    override fun setPlayerInventoryData(player: Player, data: SyncPlayerInventoryData) {
        dataMap[player.getPlayerID()] = data
    }
}