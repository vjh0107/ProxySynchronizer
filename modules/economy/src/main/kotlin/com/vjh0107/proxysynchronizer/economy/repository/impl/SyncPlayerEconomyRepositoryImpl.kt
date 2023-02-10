package com.vjh0107.proxysynchronizer.economy.repository.impl

import com.vjh0107.barcode.framework.AbstractBarcodePlugin
import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.database.player.PlayerIDWrapper
import com.vjh0107.barcode.framework.database.player.getPlayerID
import com.vjh0107.barcode.framework.database.player.repository.ProxySafeSavablePlayerDataRepository
import com.vjh0107.barcode.framework.koin.annotation.BarcodeSingleton
import com.vjh0107.proxysynchronizer.economy.SyncPlayerEconomyData
import com.vjh0107.proxysynchronizer.economy.entity.SyncPlayerEconomyEntity
import com.vjh0107.proxysynchronizer.economy.entity.SyncPlayerEconomyTable
import com.vjh0107.proxysynchronizer.economy.repository.SyncPlayerEconomyRepository
import org.bukkit.entity.Player

@BarcodeComponent
@BarcodeSingleton(binds = [SyncPlayerEconomyRepository::class])
class SyncPlayerEconomyRepositoryImpl(
    plugin: AbstractBarcodePlugin
) : ProxySafeSavablePlayerDataRepository<SyncPlayerEconomyData>(plugin), SyncPlayerEconomyRepository {
    override fun getTablesToLoad() = listOf(SyncPlayerEconomyTable)

    override suspend fun loadDataProxySafely(id: PlayerIDWrapper): SyncPlayerEconomyData {
        return dataSource.query {
            val result = SyncPlayerEconomyEntity.findByProfileID(id.profileID) ?: SyncPlayerEconomyEntity.new(id)
            SyncPlayerEconomyData(result.money)
        }
    }

    override suspend fun saveDataProxySafely(id: PlayerIDWrapper, data: SyncPlayerEconomyData) {
        dataSource.query {
            with(SyncPlayerEconomyEntity.findByProfileID(id.profileID)) {
                if (this == null) {
                    throw NullPointerException("profileID ${id.profileID} 를 통해 플레이어를 찾을 수 없습니다. 데이터 저장을 건너뜁니다.")
                }
                this.money = data.money
                this.updatedAt = java.time.LocalDateTime.now()
            }
        }
    }

    override fun getSyncPlayerData(id: PlayerIDWrapper): SyncPlayerEconomyData {
        return getPlayerData(id) ?: throw NullPointerException("id $id 를 통해 플레이어 인벤토리를 찾을 수 없습니다.")
    }

    override fun getSyncPlayerData(player: Player): SyncPlayerEconomyData {
        return getSyncPlayerData(player.getPlayerID())
    }

    override fun setSyncPlayerData(id: PlayerIDWrapper, data: SyncPlayerEconomyData) {
        dataMap[id] = data
    }

    override fun setSyncPlayerData(player: Player, data: SyncPlayerEconomyData) {
        dataMap[player.getPlayerID()] = data
    }
}