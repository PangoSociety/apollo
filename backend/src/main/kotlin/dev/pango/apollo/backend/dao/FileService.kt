package dev.pango.apollo.backend.dao

import dev.pango.apollo.backend.models.*

class FileService {
    fun createFile(filesRequest: FilesRequest): Boolean {
//        val newFile =
//            File {
//                id = filesRequest.id
//                path = filesRequest.path
//            }
//        val affectedRecordsNumber =
//            database.sequenceOf(Files)
//                .add(newFile)
//        return affectedRecordsNumber == 1
        return true
    }

    fun findFileById(fileId: String): File {
//        database.sequenceOf(Files).single { it.id eq UUID.fromString(fileId) }
        TODO()
    }
}
