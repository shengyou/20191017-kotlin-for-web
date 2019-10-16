package io.ktor.web

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    routing {

        get("/") {
            call.respondHtml {
                head {
                    title { +"HTML DSL" }
                }
                body {
                    ul("pagination") {
                        li { +"Item 1" }
                        li { +"Item 2" }
                        li { +"Item 3" }
                    }
                }
            }
        }

    }

}
