package org.jonnyzzz.kotlin.fractals2


class MandelbrotPointIteration(
  private val c: Complex
) : Iterator<MandelbrotPointIteration> {
  private var z = Complex.ZERO

  var iteration = 0
    private set

  var mod2 = 0.0
    private set

  override fun next(): MandelbrotPointIteration {
    z = z * z + c
    mod2 = z.mod2

    iteration++

    return this
  }

  override fun hasNext() = mod2 < 4
}
