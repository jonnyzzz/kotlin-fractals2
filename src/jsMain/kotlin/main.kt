package org.jonnyzzz.kotlin.fractals2

import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.id
import kotlinx.html.js.button
import kotlinx.html.js.canvas
import kotlinx.html.js.h1
import kotlinx.html.js.h2
import kotlinx.html.js.img
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.removeClass

const val jvmBackend = "http://$jvmHost:$jvmPort"

val fractalCanvasElement: HTMLCanvasElement by document
val fractalImageElement: HTMLImageElement by document

fun main() {
  document.getElementById("app")?.also { it.innerHTML = "" }?.append {
    h1 { +"Kotlin Fractals" }
    h2 { +"JS Edition" }
    div(classes = "controls") {
      button {
        +"Render in JS"
        onClickFunction = {
          renderToCanvas(fractalCanvasElement) { image ->
            MandelbrotRender.justRender(300, image, MandelbrotRender.initialArea)
          }
          fractalCanvasElement.removeClass("hidden")
          fractalImageElement.addClass("hidden")
        }
      }
      button {
        +"Render in JVM"
        onClickFunction = {
          fractalImageElement.removeClass("hidden")
          fractalCanvasElement.addClass("hidden")
        }
      }
    }

    img {
      id = ::fractalImageElement.name
      classes += "fractalImage"
      classes += "hidden"
      src = "$jvmBackend/mandelbrot"
      width = "600"
      height = "600"
    }

    canvas {
      id = ::fractalCanvasElement.name
      classes += "fractalImage"
      classes += "hidden"
      width = "600"
      height = "600"
    }
  }
}
