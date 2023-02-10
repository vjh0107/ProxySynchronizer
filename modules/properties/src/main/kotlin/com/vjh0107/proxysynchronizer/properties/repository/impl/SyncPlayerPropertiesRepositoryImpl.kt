package com.vjh0107.proxysynchronizer.properties.repository.impl

import com.vjh0107.barcode.framework.AbstractBarcodePlugin
import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.database.player.PlayerIDWrapper
import com.vjh0107.barcode.framework.database.player.getPlayer
import com.vjh0107.barcode.framework.database.player.getPlayerID
import com.vjh0107.barcode.framework.database.player.repository.ProxySafeSavablePlayerDataRepository
import com.vjh0107.barcode.framework.koin.annotation.BarcodeSingleton
import com.vjh0107.barcode.framework.koin.injector.inject
import com.vjh0107.barcode.framework.serialization.deserialize
import com.vjh0107.barcode.framework.serialization.serialize
import com.vjh0107.proxysynchronizer.properties.SyncPlayerPropertiesData
import com.vjh0107.proxysynchronizer.properties.entity.SyncPlayerPropertiesTable
import com.vjh0107.proxysynchronizer.properties.entity.SyncPlayerPropertiesEntity
import com.vjh0107.proxysynchronizer.properties.repository.SyncPlayerPropertiesRepository
import com.vjh0107.proxysynchronizer.properties.util.PotionEffectSerializer
import io.ktor.http.*
import kotlinx.serialization.builtins.ListSerializer
import org.bukkit.entity.Player
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import java.time.LocalDateTime

@BarcodeComponent
@BarcodeSingleton(binds = [SyncPlayerPropertiesRepository::class])
class SyncPlayerPropertiesRepositoryImpl(
    plugin: AbstractBarcodePlugin
) : ProxySafeSavablePlayerDataRepository<SyncPlayerPropertiesData>(plugin), SyncPlayerPropertiesRepository {
    override fun getTablesToLoad() = listOf(SyncPlayerPropertiesTable)

    override suspend fun loadDataProxySafely(id: PlayerIDWrapper): SyncPlayerPropertiesData {
        return dataSource.query {
            val result = SyncPlayerPropertiesEntity.findByProfileID(id.profileID)
            if (result == null) {
                val data: SyncPlayerPropertiesData by inject(named("default")) { parametersOf(id.getPlayer()) }
                data
            } else {
                val data: SyncPlayerPropertiesData by inject(named("entity")) { parametersOf(result) }
                data
            }
        }
    }

    override suspend fun saveDataProxySafely(id: PlayerIDWrapper, data: SyncPlayerPropertiesData) {
        dataSource.query {
            val result = SyncPlayerPropertiesEntity.findByProfileID(id.profileID)
            if (result == null) {
                SyncPlayerPropertiesEntity.new(id) new@{ saveData(this@new, data) }
            } else {
                saveData(result, data)
            }
        }
    }

    private fun saveData(entity: SyncPlayerPropertiesEntity, data: SyncPlayerPropertiesData) {
        entity.health = data.health
        entity.gameMode = data.gameMode.name
        entity.cooldowns = data.cooldowns.serialize()
        entity.heldItemSlot = data.heldItemSlot
        entity.potions = data.potions.serialize(ListSerializer(PotionEffectSerializer))
    }

    override fun getSyncPlayerData(id: PlayerIDWrapper): SyncPlayerPropertiesData {
        return getPlayerData(id) ?: throw NullPointerException("id $id 를 통해 플레이어 인벤토리를 찾을 수 없습니다.")
    }

    override fun getSyncPlayerData(player: Player): SyncPlayerPropertiesData {
        return getSyncPlayerData(player.getPlayerID())
    }

    override fun setSyncPlayerData(id: PlayerIDWrapper, data: SyncPlayerPropertiesData) {
        dataMap[id] = data
    }

    override fun setSyncPlayerData(player: Player, data: SyncPlayerPropertiesData) {
        dataMap[player.getPlayerID()] = data
    }
}