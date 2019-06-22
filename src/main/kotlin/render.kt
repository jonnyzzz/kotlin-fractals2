package org.jonnyzzz.kotlin.fractals2

import java.awt.Color

interface FractalImage {
  val pixelRect: Rect<Int>
  fun putPixel(p: Pixel, c: Color)
}

typealias MandelbrotRenderTask = () -> Unit

object MandelbrotRender {
  val initialArea = Rect(-2.0, -2.0, 2.0, 2.0)

  fun justRender(maxIterations: Int = 1500,
                 image: FractalImage,
                 area: Rect<Double>) {

    justRenderTasks(
            maxIterations = maxIterations,
            image = image,
            area = area).forEach { it() }
  }

  fun justRenderTasks(maxIterations: Int = 1500,
                      chunk: Int = 100,
                      image: FractalImage,
                      area: Rect<Double>): Sequence<MandelbrotRenderTask> {

    val t = Transformation(image.pixelRect, area)
    return sequence {
      t.forEachPixel { p, c ->
        yield(p to c)
      }
    }.chunked(chunk).map { tasks ->
      {
        tasks.forEach { (p, c) ->
          val pt = MandelbrotPointIteration(c)
                  .asSequence()
                  .take(maxIterations)
                  .last()

          image.putPixel(p, ColorPicker.selectColour(pt))
        }
      }
    }
  }
}
