package com.example.dao

import com.example.models.*
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import java.util.UUID

class FileService {
    private val database = Database.connect(
        url = "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres"
    )

    fun createFile(filesRequest: FilesRequest): Boolean {
        val newFile = File {
            id = filesRequest.id
            path = filesRequest.path
        }
        val affectedRecordsNumber =
            database.sequenceOf(Files)
                .add(newFile)
        return affectedRecordsNumber == 1
    }

    fun findFileById(fileId: String): File =
        database.sequenceOf(Files).single { it.id eq UUID.fromString(fileId) }

}