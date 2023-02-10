package com.vjh0107.proxysynchronizer.inventory.repository.impl

import com.vjh0107.barcode.framework.AbstractBarcodePlugin
import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.database.player.PlayerIDWrapper
import com.vjh0107.barcode.framework.database.player.getPlayerID
import com.vjh0107.barcode.framework.database.player.repository.ProxySafeSavablePlayerDataRepository
import com.vjh0107.barcode.framework.koin.annotation.BarcodeSingleton
import com.vjh0107.barcode.framework.koin.injector.inject
import com.vjh0107.barcode.framework.serialization.serialize
import com.vjh0107.proxysynchronizer.inventory.SyncPlayerInventoryData
import com.vjh0107.proxysynchronizer.inventory.repository.SyncPlayerInventoryRepository
import com.vjh0107.proxysynchronizer.inventory.entity.SyncPlayerInventoryTable
import com.vjh0107.proxysynchronizer.inventory.entity.SyncPlayerInventoryEntity
import com.vjh0107.proxysynchronizer.core.serializer.NullableItemStackListSerializer
import org.bukkit.entity.Player
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@BarcodeComponent
@BarcodeSingleton(binds = [SyncPlayerInventoryRepository::class])
class SyncPlayerInventoryRepositoryImpl(
    plugin: AbstractBarcodePlugin
) : ProxySafeSavablePlayerDataRepository<SyncPlayerInventoryData>(plugin), SyncPlayerInventoryRepository {

    override fun getTablesToLoad() = listOf(SyncPlayerInventoryTable)

    override suspend fun loadDataProxySafely(id: PlayerIDWrapper): SyncPlayerInventoryData {
        return dataSource.query {
            val result = SyncPlayerInventoryEntity.findByProfileID(id.profileID)
            if (result == null) {
                val data: SyncPlayerInventoryData by inject(named("default"))
                data
            } else {
                val data: SyncPlayerInventoryData by inject(named("entity")) { parametersOf(result) }
                data
            }
        }
    }

    override suspend fun saveDataProxySafely(id: PlayerIDWrapper, data: SyncPlayerInventoryData) {
        dataSource.query {
            val result = SyncPlayerInventoryEntity.findByProfileID(id.profileID)
            if (result == null) {
                SyncPlayerInventoryEntity.new(id) new@{ saveData(this@new, data) }
            } else {
                saveData(result, data)
            }
        }
    }

    private fun saveData(entity: SyncPlayerInventoryEntity, data: SyncPlayerInventoryData) {
        entity.storage = data.storage.serialize(NullableItemStackListSerializer)
        entity.armors = data.armors.serialize(NullableItemStackListSerializer)
        entity.extras = data.extras.serialize(NullableItemStackListSerializer)
    }

    override fun getSyncPlayerData(id: PlayerIDWrapper): SyncPlayerInventoryData {
        return getPlayerData(id) ?: throw NullPointerException("id $id 를 통해 플레이어 인벤토리를 찾을 수 없습니다.")
    }

    override fun getSyncPlayerData(player: Player): SyncPlayerInventoryData {
        return getSyncPlayerData(player.getPlayerID())
    }

    override fun setSyncPlayerData(id: PlayerIDWrapper, data: SyncPlayerInventoryData) {
        dataMap[id] = data
    }

    override fun setSyncPlayerData(player: Player, data: SyncPlayerInventoryData) {
        dataMap[player.getPlayerID()] = data
    }
}