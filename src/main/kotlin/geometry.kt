@file:Suppress("NonAsciiCharacters")

package org.jonnyzzz.kotlin.fractals2

import kotlin.math.absoluteValue

data class Rect<T>(
  val left: T,
  val top: T,
  val right: T,
  val bottom: T
)

data class Pixel(
  val x: Int,
  val y: Int
)

data class Segment<T>(
  val left: T,
  val right: T
)

val <T> Rect<T>.X
  get() = Segment(left, right)

val <T> Rect<T>.Y
  get() = Segment(top, bottom)

fun Double.normalize() = when {
  this <= 0 -> 0.0
  this >= 1 -> 1.0
  else -> this
}

val <T : Number> Segment<T>.size
  get() = (right.toDouble() - left.toDouble()).absoluteValue

val Segment<Int>.size
  get() = (right - left).absoluteValue

fun <T : Number> Segment<T>.normalize(x: T) = ((x.toDouble() - left.toDouble()) / size).normalize()

fun <T : Number> Segment<T>.fromNorm(d: Double): Number = d.normalize() * size + left.toDouble()

class Transformation(
  val pixelRect: Rect<Int>,
  val fractalRect: Rect<Double>
) {

  private fun <From : Number, To : Number> scale(c: From,
                                                 fromScale: Segment<From>,
                                                 toScale: Segment<To>) = toScale.fromNorm(fromScale.normalize(c))

  fun toComplex(x: Int, y: Int): Complex {
    val re = scale(x, pixelRect.X, fractalRect.X).toDouble()
    val im = scale(y, pixelRect.Y, fractalRect.Y).toDouble()
    return Complex(re, im)
  }
}

fun Transformation.toComplex(c: Pixel) = toComplex(c.x, c.y)

inline fun Rect<Int>.forEachPixel(isActive: () -> Boolean = { true },
                                  action: (Pixel) -> Unit) {
  (top until bottom).forEach { y ->

    if (!isActive()) return

    (left until right).forEach { x ->
      action(Pixel(x, y))
    }
  }
}

inline fun Transformation.forEachPixel(isActive: () -> Boolean = { true },
                                       action: (Pixel, Complex) -> Unit) {
  pixelRect.forEachPixel(isActive) { p ->
    action(p, toComplex(p))
  }
}
