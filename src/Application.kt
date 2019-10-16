package io.ktor.web

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    routing {

        get("/") {
            call.respondText(
                "<!doctype html>\n<html lang='zh-Hant'>\n<head>\n    <meta charset='UTF-8'>\n    <meta name='viewport'\n          content='width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0'>\n    <meta http-equiv='X-UA-Compatible' content='ie=edge'>\n    <title>Hello, Ktor</title>\n</head>\n<body>\n<header>\n    <h1>Hello, Ktor</h1>\n    <p>This is an Hello, world example of Ktor application.</p>\n</header>\n<div id='content'>\n    <ul id='pagination'>\n        <li class='item'>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci assumenda, dolorem eos esse\n            minima molestiae mollitia nesciunt omnis quo rerum.\n        </li>\n        <li class='item'>Dolorem eum fugiat harum impedit laborum mollitia neque omnis quae qui, similique. Aut eligendi\n            exercitationem fugiat provident, quas temporibus ullam!\n        </li>\n        <li class='item'>Deleniti, obcaecati, veritatis? Accusantium ad dicta, dignissimos dolorem exercitationem quos\n            repudiandae. Dolore dolorem et fugiat in inventore itaque, labore totam.\n        </li>\n        <li class='item'>Aut beatae esse laboriosam odio voluptatem! A dicta expedita iusto quod ullam. Doloribus\n            laudantium nam nobis porro praesentium saepe similique.\n        </li>\n        <li class='item'>Aspernatur deserunt ex facilis illo ipsa obcaecati odit quibusdam vitae. Alias itaque\n            laboriosam nulla odit pariatur quam quas saepe vitae?\n        </li>\n    </ul>\n</div>\n<footer></footer>\n    \n\n\n</body>\n</html>",
                ContentType.Text.Html,
                HttpStatusCode.OK
            )
        }

    }

}
