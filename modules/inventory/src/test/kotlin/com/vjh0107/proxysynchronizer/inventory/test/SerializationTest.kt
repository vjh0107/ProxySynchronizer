package com.vjh0107.proxysynchronizer.inventory.test

import be.seeseemelk.mockbukkit.MockBukkit
import com.vjh0107.barcode.framework.serialization.serializers.ItemStackSerializer
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.json.Json
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.inventory.ItemStack

class SerializationTest : AnnotationSpec() {
    private lateinit var server: Server

    @BeforeEach
    fun setup() {
        server = MockBukkit.mock()
    }

    @Test
    fun serialize_item_and_serialized_string_should_be_greater_than_50() {
        val itemStack = ItemStack(Material.SAND)
        Json.encodeToString(ItemStackSerializer, itemStack).length shouldBeGreaterThan 50
    }

    @Test
    fun serialize_collection_and_explicit_serializer_should_be_work() {
        val itemStacks = listOf(ItemStack(Material.SAND), ItemStack(Material.SANDSTONE), null, ItemStack(Material.IRON_AXE, 3))
        Json.encodeToString(ListSerializer(ItemStackSerializer.nullable), itemStacks).contains("null,") shouldBe true
    }

    @Test
    fun deserialize_null_should_be_string_null() {
        Json.decodeFromString(ItemStackSerializer.nullable, "null") shouldBe null
    }

    @AfterEach
    fun teardown() {
        MockBukkit.unmock()
    }
}