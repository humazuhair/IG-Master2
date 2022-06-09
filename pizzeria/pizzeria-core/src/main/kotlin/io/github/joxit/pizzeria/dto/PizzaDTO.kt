package io.github.joxit.pizzeria.dto

/**
 * Old Java version : https://github.com/Joxit/IG-Master2/blob/promo-2021/pizzeria/pizzeria-core/src/main/java/io/github/joxit/pizzeria/
 * @author Jones Magloire (@Joxit)
 * @since Java: 2017-11-01
 * @since Kotlin: 2022
 */
data class PizzaDTO(
  val name: String,
  val prix: Float,
  val ingredients: Collection<String>
)