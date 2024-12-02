package com.example.tenmin.ui.model

data class Zila(
  val id: Int,
  val name: String,
  val state: String,
  val country: String,
  val coord: Coordinates
)

data class Coordinates(
  val lon: Double,
  val lat: Double
)