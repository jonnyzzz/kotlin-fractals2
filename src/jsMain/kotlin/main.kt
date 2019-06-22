package org.jonnyzzz.kotlin.fractals2

import kotlinx.html.dom.append
import kotlinx.html.js.h2
import kotlinx.html.js.img
import kotlin.browser.document

fun main() {
  document.getElementById("app")?.also { it.innerHTML = "" }?.append {
    h2 { +" JS Mandelbrot" }
    img(src = "/mandelbrot") { }
  }
}
