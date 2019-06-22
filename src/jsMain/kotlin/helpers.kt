package org.jonnyzzz.kotlin.fractals2

import org.w3c.dom.Document
import org.w3c.dom.Element
import kotlin.dom.addClass
import kotlin.dom.hasClass
import kotlin.dom.removeClass
import kotlin.reflect.KProperty


inline operator fun <reified T> Document.getValue(x: Any?, kProperty: KProperty<*>) =
  getElementById(kProperty.name) as T

fun Element.toggleClass(name: String) {
  if (hasClass(name)) {
    removeClass(name)
  } else {
    addClass(name)
  }
}

