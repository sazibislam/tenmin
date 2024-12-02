package com.example.tenmin.ui

import androidx.lifecycle.ViewModel
import com.example.tenmin.ui.model.Zila
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel: ViewModel() {
  private val _selectedZila = MutableStateFlow<Zila?>(null)
  val selectedZila: StateFlow<Zila?> = _selectedZila

  fun setSelectedZila(zila: Zila) {
    _selectedZila.value = zila
  }
}
