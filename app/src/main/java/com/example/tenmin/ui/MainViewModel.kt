package com.example.tenmin.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.WeatherResponse
import com.example.tenmin.domain.repository.HomeRepository
import com.example.tenmin.ui.model.Zila
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
  private val repository: HomeRepository
) : ViewModel() {

  private val _weatherState = MutableStateFlow<WeatherResponse?>(null)
  val weatherState: StateFlow<WeatherResponse?> = _weatherState

  fun getWeatherData(selectedZila: Zila?) {

    println("getWeatherData Api called")

    viewModelScope.launch(Dispatchers.IO) {
      selectedZila?.coord?.let { cord ->
        repository.getWeatherData(cord).collect { response ->
          when (response) {
            is ResponseResource.Error -> {
              Log.d("MainViewModel", "Some error happened ${response.errorMessage}")
            }

            is ResponseResource.Success -> {
              _weatherState.value = response.data
              Log.d("MainViewModel", "${response.data}")
            }
          }
        }
      }
    }
  }
}
