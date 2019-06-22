@file:Suppress("NonAsciiCharacters")

package org.jonnyzzz.kotlin.fractals2

class Transformation(
  val pixelRect: Rect<Int>,
  val fractalRect: Rect<Double>
)

inline fun Rect<Int>.forEachPixel(action: (Pixel) -> Unit) {
  (top until bottom).forEach { y ->
    (left until right).forEach { x ->
      action(Pixel(x, y))
    }
  }
}

inline fun Transformation.forEachPixel(action: (Pixel, Complex) -> Unit) {
  pixelRect.forEachPixel { p ->
    val re = p.x.scale(pixelRect.X, fractalRect.X).toDouble()
    val im = p.y.scale(pixelRect.Y, fractalRect.Y).toDouble()
    action(p, Complex(re, im))
  }
}
