package org.jonnyzzz.kotlin.fractals2


data class Rect<T>(
  val left: T,
  val top: T,
  val right: T,
  val bottom: T
)

val <T> Rect<T>.X
  get() = Segment(left, right)

val <T> Rect<T>.Y
  get() = Segment(top, bottom)

