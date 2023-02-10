package com.vjh0107.proxysynchronizer.properties.test

import be.seeseemelk.mockbukkit.MockBukkit
import com.vjh0107.barcode.framework.serialization.serialize
import com.vjh0107.barcode.framework.utils.print
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Server

class SerializationTest : AnnotationSpec() {
    private lateinit var server: Server

    @BeforeEach
    fun setup() {
        server = MockBukkit.mock()
    }

    @Test
    fun enum_serialization_text_literal_should_be_lowercase() {
        Json.encodeToString(GameMode.ADVENTURE).contains("ADVENTURE") shouldBe true
    }

    @Test
    fun serialize_map_should_be_found_context_by_serializer() {
        val map: MutableMap<Material, Int> = mutableMapOf()
        map[Material.SANDSTONE] = 1
        map[Material.SAND] = 2
        map.serialize().print().length shouldBeGreaterThan 1
    }

    @AfterEach
    fun teardown() {
        MockBukkit.unmock()
    }
}