package dev.pango.apollo.backend.dao

import dev.pango.apollo.backend.models.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.persistence.*
import org.ktorm.dsl.*
import org.ktorm.entity.*
import java.util.*

class FileService {
    fun createFile(filesRequest: FilesRequest): Boolean {
        val newFile =
            File {
                id = filesRequest.id
                path = filesRequest.path
            }
        val affectedRecordsNumber =
            database.sequenceOf(Files)
                .add(newFile)
        return affectedRecordsNumber == 1
    }

    fun findFileById(fileId: String): File = database.sequenceOf(Files).single { it.id eq UUID.fromString(fileId) }
}
