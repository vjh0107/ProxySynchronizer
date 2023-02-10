package com.vjh0107.proxysynchronizer.enderchest.repository.impl

import com.vjh0107.barcode.framework.AbstractBarcodePlugin
import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.database.player.PlayerIDWrapper
import com.vjh0107.barcode.framework.database.player.getPlayerID
import com.vjh0107.barcode.framework.database.player.repository.ProxySafeSavablePlayerDataRepository
import com.vjh0107.barcode.framework.koin.annotation.BarcodeSingleton
import com.vjh0107.barcode.framework.koin.injector.inject
import com.vjh0107.barcode.framework.serialization.serialize
import com.vjh0107.proxysynchronizer.core.serializer.NullableItemStackListSerializer
import com.vjh0107.proxysynchronizer.enderchest.SyncPlayerEnderChestData
import com.vjh0107.proxysynchronizer.enderchest.entity.SyncPlayerEnderChestEntity
import com.vjh0107.proxysynchronizer.enderchest.entity.SyncPlayerEnderChestTable
import com.vjh0107.proxysynchronizer.enderchest.repository.SyncPlayerEnderChestRepository
import org.bukkit.entity.Player
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@BarcodeComponent
@BarcodeSingleton(binds = [SyncPlayerEnderChestRepository::class])
class SyncPlayerEnderChestRepositoryImpl(
    plugin: AbstractBarcodePlugin
) : ProxySafeSavablePlayerDataRepository<SyncPlayerEnderChestData>(plugin), SyncPlayerEnderChestRepository {

    override fun getTablesToLoad() = listOf(SyncPlayerEnderChestTable)

    override suspend fun loadDataProxySafely(id: PlayerIDWrapper): SyncPlayerEnderChestData {
        return dataSource.query {
            val result = SyncPlayerEnderChestEntity.findByProfileID(id.profileID)
            if (result == null) {
                val data: SyncPlayerEnderChestData by inject(named("default"))
                data
            } else {
                val data: SyncPlayerEnderChestData by inject(named("entity")) { parametersOf(result) }
                data
            }
        }
    }

    override suspend fun saveDataProxySafely(id: PlayerIDWrapper, data: SyncPlayerEnderChestData) {
        dataSource.query {
            val result = SyncPlayerEnderChestEntity.findByProfileID(id.profileID)
            if (result == null) {
                SyncPlayerEnderChestEntity.new(id) new@{ saveData(this@new, data) }
            } else {
                saveData(result, data)
            }
        }
    }

    private fun saveData(entity: SyncPlayerEnderChestEntity, data: SyncPlayerEnderChestData) {
        entity.contents = data.contents.serialize(NullableItemStackListSerializer)
    }

    override fun getSyncPlayerData(id: PlayerIDWrapper): SyncPlayerEnderChestData {
        return getPlayerData(id) ?: throw NullPointerException("id $id 를 통해 플레이어 인벤토리를 찾을 수 없습니다.")
    }

    override fun getSyncPlayerData(player: Player): SyncPlayerEnderChestData {
        return getSyncPlayerData(player.getPlayerID())
    }

    override fun setSyncPlayerData(id: PlayerIDWrapper, data: SyncPlayerEnderChestData) {
        dataMap[id] = data
    }

    override fun setSyncPlayerData(player: Player, data: SyncPlayerEnderChestData) {
        dataMap[player.getPlayerID()] = data
    }
}