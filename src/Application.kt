package io.ktor.web

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.web.entities.Task
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    Database.connect(
        url = "jdbc:mysql://127.0.0.1:8889/todo_local?useUnicode=true&characterEncoding=utf-8&useSSL=false",
        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "root"
    )

    routing {

        static("static") {
            resources("static")
        }

        get("/") {
            val tasks = transaction {
                Task.all().sortedByDescending { it.id }.toList()
            }

            call.respond(FreeMarkerContent("index.ftl", mapOf("tasks" to tasks)))
        }

        post("/tasks") {
            val params = call.receiveParameters()

            transaction {
                Task.new {
                    title = params["title"].toString()
                    completed = params["completed"]?.toBoolean() ?: false
                    createdAt = DateTime.now()
                    updatedAt = DateTime.now()
                }
            }

            call.respondRedirect("/")
        }

        post("tasks/{id}/completed") {
            val id = call.parameters["id"]!!.toInt()
            transaction {
                val task = Task.findById(id)!!
                task.completed = true
            }

            call.respondRedirect("/")
        }

        post("/tasks/{id}/delete") {
            val id = call.parameters["id"]!!.toInt()
            transaction {
                val task = Task.findById(id)!!
                task.delete()
            }

            call.respondRedirect("/")
        }

    }

}
