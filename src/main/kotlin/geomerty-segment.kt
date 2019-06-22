package org.jonnyzzz.kotlin.fractals2

import kotlin.math.absoluteValue

data class Segment<T>(
  val left: T,
  val right: T
)

private fun Double.normalize() = when {
  this <= 0 -> 0.0
  this >= 1 -> 1.0
  else -> this
}

private val <T : Number> Segment<T>.size
  get() = (right.toDouble() - left.toDouble()).absoluteValue

private fun <T : Number> Segment<T>.normalize(x: T) = ((x.toDouble() - left.toDouble()) / size).normalize()

private fun <T : Number> Segment<T>.fromNorm(d: Double) = d.normalize() * size + left.toDouble()

fun <From : Number, To : Number> From.scale(
                                       fromScale: Segment<From>,
                                       toScale: Segment<To>): Number {
  return toScale.fromNorm(fromScale.normalize(this))
}
