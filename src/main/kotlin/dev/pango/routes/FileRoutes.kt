package dev.pango.routes

import JwtConfig
import dev.pango.dao.*
import dev.pango.models.*
import dev.pango.plugins.extractFileExtension
import dev.pango.plugins.generateFileName
import dev.pango.plugins.hashFileName
import io.ktor.http.*
import io.ktor.http.content.forEachPart
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import java.io.File
import io.ktor.http.content.PartData.FileItem
import io.ktor.http.content.streamProvider
import java.security.MessageDigest
import java.util.UUID


fun Application.configureFileRoutes() {
    routing {
        val fileService = dev.pango.dao.FileService()
        route("/file") {
            createFile(fileService)
        }
        getFileForDownload(fileService)

    }
}


fun Route.getFileForDownload(fileService: dev.pango.dao.FileService) {
    get("/download/{fileId}") {
        val fileId = call.parameters["fileId"] ?: return@get call.respond(
            HttpStatusCode.BadRequest,
            "Missing fileId parameter"
        )
        val fileInfo = fileService.findFileById(fileId)
        val fullPath = fileInfo.path
        val file = File(fullPath)
        if (!file.exists()) {
            return@get call.respond(HttpStatusCode.NotFound, "File not found on the server")
        }
        call.response.header(
            HttpHeaders.ContentDisposition,
            ContentDisposition.Attachment.withParameter(ContentDisposition.Parameters.FileName,
                "${fileInfo.id}"
            ).toString()
        )
        call.respondFile(file)
    }
}

fun Route.createFile(fileService: dev.pango.dao.FileService) {
    post {
        val parts = call.receiveMultipart()
        val uuid: UUID = generateFileName()
        var fileName: String? = null
        parts.forEachPart { part ->
            when (part) {
                is FileItem -> {
                    val file = File(
                        "assets/${uuid}.${
                            part.originalFileName?.let { it1 ->
                                extractFileExtension(
                                    it1
                                )
                            }
                        }"
                    )
                    file.writeBytes(part.streamProvider().readBytes())
                    fileName = file.path
                }

                else -> {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ErrorResponse("Cannot create path File")
                    )
                }
            }
            part.dispose()
        }


        fileName?.let {
//            val hashedFileName = hashFileName(it)
            val success = fileService.createFile(FilesRequest(id = uuid, path = it))
            if (success)
                call.respond(HttpStatusCode.Created)
            else
                call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot create path File"))
        }
        call.respond("File uploaded successfully")
    }

}
