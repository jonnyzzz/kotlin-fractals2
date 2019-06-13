package org.jonnyzzz.kotlin.fractals2

import www.camick.com.HSLColor
import java.awt.Color
import kotlin.math.ln

object Colors

fun Colors.hsl(h: Double,
               s: Double,
               l: Double): Color = HSLColor.toRGB(h.toFloat(), s.toFloat(), l.toFloat())

object ColorPicker {
  fun selectColour(z: MandelbrotPointIteration): Color {
    if (z.hasNext()) {
      return Color.BLACK
    }

    val s = z.iteration + 1 - ln(ln(z.mod2) / 2.0) / ln(2.0)
    return Colors.hsl(30.0 + 10 * s, 90.0, 50.0)
  }
}
