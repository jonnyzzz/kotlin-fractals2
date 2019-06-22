package org.jonnyzzz.kotlin.fractals2

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.html.respondHtml
import io.ktor.http.CacheControl
import io.ktor.http.HttpStatusCode
import io.ktor.response.cacheControl
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.img
import java.awt.image.BufferedImage


private inline fun <T : Any> ApplicationCall.param(name: String, convert: String.() -> T?, def: T)
  = request.queryParameters[name]?.convert() ?: def

fun ApplicationCall.paramInt(name: String, def: Int) = param(name, { toIntOrNull() }, def)
fun ApplicationCall.paramDouble(name: String, def: Double) = param(name, { toDoubleOrNull() }, def)
fun ApplicationCall.paramBoolean(name: String, def: Boolean = false) = param(name, { toBoolean() }, def)

fun main() {

  embeddedServer(Netty, host = "127.0.0.1", port = 8888) {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
      get("/") {
        call.respondHtml {
          body {
            h1 { +"Kotlin Fractals" }
            img(src = "/mandelbrot")
          }
        }
      }

      get("/mandelbrot") {
        val jvm = call.paramBoolean("jvm")

        val width = call.paramInt("width", 800)
        val height = call.paramInt("height", 600)

        val top = call.paramDouble("top", MandelbrotRender.initialArea.top)
        val right = call.paramDouble("right", MandelbrotRender.initialArea.right)
        val bottom = call.paramDouble("bottom", MandelbrotRender.initialArea.bottom)
        val left = call.paramDouble("left", MandelbrotRender.initialArea.left)

        val rect = Rect(top = top, left = left, bottom = bottom, right = right)

        println("Rendering image! $width x $height: $rect, jvm=$jvm")

        val img = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        MandelbrotRender.justRender(
          image = FractalGraphics(img),
          maxIterations = 1_000,
          area = rect
        )

        val message = img.toMessage()

        call.response.cacheControl(CacheControl.NoCache(CacheControl.Visibility.Public))
        call.respond(status = HttpStatusCode.OK, message = message)
      }
    }
  }.start(true)
}
