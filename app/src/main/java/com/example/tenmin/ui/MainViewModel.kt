package com.example.tenmin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tenmin.data.ResponseResource
import com.example.tenmin.data.remote.response.AlbumResponse
import com.example.tenmin.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
  private val repository: HomeRepository
) : ViewModel() {

  private val _albumState = MutableStateFlow<List<AlbumResponse>?>(null)
  val albumState: StateFlow<List<AlbumResponse>?> = _albumState

  init {
    getAlbumData()
  }

  private fun getAlbumData() {

    viewModelScope.launch(Dispatchers.IO) {
      repository.getAlbumData().collectLatest { response ->
        when (response) {
          is ResponseResource.Error -> {
            println("MainViewModel: Some error happened ${response.errorMessage}")
          }

          is ResponseResource.Success -> {
            _albumState.value = response.data
            println("MainViewModel: ${response.data}")
          }
        }
      }
    }
  }
}
