package com.vjh0107.proxysynchronizer.test

import com.vjh0107.barcode.framework.serialization.serialize
import com.vjh0107.proxysynchronizer.inventory.SyncPlayerInventoryData
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldNotBe

class SerializationTest : AnnotationSpec() {
    @Test
    fun serializationTest() {
        val inventoryData = SyncPlayerInventoryData("string1", "string2")
        inventoryData.isCompletelyLoaded = true
        inventoryData.serialize() shouldNotBe null
    }
}