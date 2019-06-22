@file:Suppress("NonAsciiCharacters")

package org.jonnyzzz.kotlin.fractals2

data class Pixel(
  val x: Int,
  val y: Int
)

data class Rect<T>(
  val left: T,
  val top: T,
  val right: T,
  val bottom: T
)

class Transformation(
  val pixelRect: Rect<Int>,
  val fractalRect: Rect<Double>
)

inline fun Transformation.forEachPixel(action: (Pixel, Complex) -> Unit) {
  val xToRe = (fractalRect.right - fractalRect.left) / (pixelRect.right - pixelRect.left)
  val yToIm = (fractalRect.bottom - fractalRect.top) / (pixelRect.bottom - pixelRect.top)

  (pixelRect.top until pixelRect.bottom).forEach { y ->
    (pixelRect.left until pixelRect.right).forEach { x ->

      val re = x * xToRe + fractalRect.left
      val im = y * yToIm + fractalRect.top

      action(Pixel(x, y), Complex(re, im))
    }
  }
}
