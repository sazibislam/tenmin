package com.example.tenmin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tenmin.data.remote.response.WeatherResponse
import com.example.tenmin.ui.model.Coordinates
import com.example.tenmin.ui.model.Zila
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

  private val client = HttpClient()
  private val _weatherState = MutableStateFlow<WeatherResponse?>(null)
  val weatherState: StateFlow<WeatherResponse?> = _weatherState

  private val _selectedZila = MutableStateFlow<Zila?>(getDefaultZila())
  val selectedZila: StateFlow<Zila?> = _selectedZila

  fun setSelectedZila(zila: Zila) {
    _selectedZila.value = zila
    getCurrentWeatherData()
  }

  private fun getDefaultZila() =
    Zila(
      id = 1185241,
      state = "",
      country = "BD",
      name = "Dhaka",
      coord = Coordinates(90.40744, 23.7104)
    )

  private fun getCurrentWeatherData() {

    val lat = selectedZila.value?.coord?.lat
    val lon = selectedZila.value?.coord?.lon
    val apikey = "86c282621580b8120fd7479e434e4114"

    println("Api calling")

    val url = "https://api.openweathermap.org/data/3.0/onecall?lat=$lat&lon=$lon&appid=$apikey"

    viewModelScope.launch {
      try {
        val response = client.get(url) {

        }.body<WeatherResponse>()
        when (response.cod) {
          200 -> {}
          else -> {}
        }
      } catch (e: Exception) {
        WeatherResponse(message = "${e.message}")
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    client.close()
  }
}