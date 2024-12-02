package com.example.tenmin.ui

import androidx.lifecycle.ViewModel
import com.example.tenmin.ui.model.Coordinates
import com.example.tenmin.ui.model.Zila
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {

  private val _selectedZila = MutableStateFlow<Zila?>(getDefaultZila())
  val selectedZila: StateFlow<Zila?> = _selectedZila

  fun setSelectedZila(zila: Zila) {
    _selectedZila.value = zila
  }

  private fun getDefaultZila() =
    Zila(
      id = 1185241,
      state = "",
      country = "BD",
      name = "Dhaka",
      coord = Coordinates(90.40744, 23.7104)
    )
}