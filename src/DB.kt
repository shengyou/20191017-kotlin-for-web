package io.ktor.web

import io.ktor.web.databases.Tasks
import io.ktor.web.entities.Task
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

fun main() {
    Database.connect(
        url = "jdbc:mysql://127.0.0.1:8889/todo_local?useUnicode=true&characterEncoding=utf-8&useSSL=false",
        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "root"
    )

    transaction {
        SchemaUtils.drop(Tasks)
        SchemaUtils.create(Tasks)
    }

    transaction {
        for (i in 1..20) {
            Task.new {
                title = "Task $i"
                completed = false
                createdAt = DateTime.now()
                updatedAt = DateTime.now()
            }
        }
    }

    transaction {
        for (task in Task.all()) {
            println("${task.title} - ${task.createdAt.toString("yyyy-MM-dd")}")
        }
    }
}