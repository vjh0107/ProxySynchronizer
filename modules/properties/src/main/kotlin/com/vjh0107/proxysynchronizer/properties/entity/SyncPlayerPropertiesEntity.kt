package com.vjh0107.proxysynchronizer.properties.entity

import com.vjh0107.barcode.framework.database.exposed.entity.BarcodeEntityClass
import com.vjh0107.barcode.framework.database.exposed.entity.BarcodePlayerEntity
import com.vjh0107.barcode.framework.database.exposed.extensions.json
import com.vjh0107.barcode.framework.database.exposed.table.BarcodeIDTable
import org.jetbrains.exposed.dao.id.EntityID

class SyncPlayerPropertiesEntity(id: EntityID<Int>) : BarcodePlayerEntity<SyncPlayerPropertiesTable>(id, SyncPlayerPropertiesTable) {
    companion object : BarcodeEntityClass<SyncPlayerPropertiesEntity>(SyncPlayerPropertiesTable)

    var health by SyncPlayerPropertiesTable.health
    var gameMode by SyncPlayerPropertiesTable.gameMode
    var cooldowns by SyncPlayerPropertiesTable.cooldowns
    var heldItemSlot by SyncPlayerPropertiesTable.heldItemSlot
    var potions by SyncPlayerPropertiesTable.potions
}

object SyncPlayerPropertiesTable : BarcodeIDTable("proxysynchronizer_properties") {
    val health = double("health").default(20.0)
    val gameMode = text("gameMode").clientDefault { "adventure" }
    val cooldowns = json("cooldowns")
    val heldItemSlot = integer("heldItemSlot").default(0)
    val potions = json("potions")
}