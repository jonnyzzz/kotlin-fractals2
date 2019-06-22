package org.jonnyzzz.kotlin.fractals2

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement


inline fun renderToCanvas(canvas : HTMLCanvasElement, action: (FractalImage) -> Unit) {
  val image = JSFractalImage(canvas.getContext("2d") as CanvasRenderingContext2D)
  try {
    action(image)
  } finally {
    image.commit()
  }
}

class JSFractalImage(
  private val ctx: CanvasRenderingContext2D
) : FractalImage {
  init {
    ctx.clearRect(0.0, 0.0, ctx.canvas.width.toDouble(), ctx.canvas.height.toDouble())
  }

  private val imageData by lazy {
    ctx.createImageData(
      ctx.canvas.width.toDouble(),
      ctx.canvas.height.toDouble())
  }

  override val pixelRect
    get() = Rect(left = 0, top = 0, right = imageData.width, bottom = imageData.height)

  override fun putPixel(p: Pixel, c: Color) {
    val base = 4 * (p.x + imageData.width * p.y)

    val image : dynamic = imageData.data

    image[base + 0] = c.r
    image[base + 1] = c.g
    image[base + 2] = c.b
    image[base + 3] = 255
  }

  fun commit() {
    println("Commit image to ctx: width=${imageData.width}, height=${imageData.height}")
    ctx.putImageData(imageData, 0.0, 0.0)
  }
}
