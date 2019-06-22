package org.jonnyzzz.kotlin.fractals2

const val jvmHost = "127.0.0.1"
const val jvmPort = 8888


expect class Color

object Colors
expect fun Colors.newColor(r: Int, g: Int, b: Int): Color
expect val Colors.BLACK : Color
