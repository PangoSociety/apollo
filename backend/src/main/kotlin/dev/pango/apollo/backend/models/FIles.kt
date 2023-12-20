package dev.pango.apollo.backend.models

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import org.ktorm.entity.*
import org.ktorm.schema.*
import java.util.*

@Serializable
data class FilesRequest(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val path: String,
)

@Serializable
data class FilesResponse(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val path: String,
)

interface File : Entity<File> {
    companion object : Entity.Factory<File>()

    var id: UUID?
    var path: String
}

object Files : Table<File>("files") {
    val id = uuid("file_id").primaryKey().bindTo(File::id)
    val path = varchar("path").bindTo(File::path)
}

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        return UUID.fromString(decoder.decodeString())
    }

    override fun serialize(
        encoder: Encoder,
        value: UUID,
    ) {
        encoder.encodeString(value.toString())
    }
}
