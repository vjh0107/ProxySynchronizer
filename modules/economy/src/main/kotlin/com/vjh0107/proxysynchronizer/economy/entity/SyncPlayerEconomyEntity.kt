package com.vjh0107.proxysynchronizer.economy.entity

import com.vjh0107.barcode.framework.database.exposed.entity.BarcodeEntityClass
import com.vjh0107.barcode.framework.database.exposed.entity.BarcodePlayerEntity
import com.vjh0107.barcode.framework.database.exposed.table.BarcodeIDTable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

class SyncPlayerEconomyEntity(id: EntityID<Int>) : BarcodePlayerEntity<SyncPlayerEconomyTable>(id, SyncPlayerEconomyTable) {
    companion object : BarcodeEntityClass<SyncPlayerEconomyEntity>(SyncPlayerEconomyTable)

    var money by SyncPlayerEconomyTable.money
    var createdAt by SyncPlayerEconomyTable.createdAt
    var updatedAt by SyncPlayerEconomyTable.updatedAt
}

object SyncPlayerEconomyTable : BarcodeIDTable("proxysynchronizer_economy") {
    val money = double("money")
    val createdAt = datetime("created_at").default(LocalDateTime.now()).index()
    val updatedAt = datetime("updated_at").default(LocalDateTime.now()).index()
}