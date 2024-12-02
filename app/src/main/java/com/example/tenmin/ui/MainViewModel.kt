package com.example.tenmin.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tenmin.data.ResponseResource
import com.example.tenmin.domain.repository.HomeRepository
import com.example.tenmin.utils.AppConstants.APP_SUCCESS_MESSAGE
import com.example.tenmin.utils.networking.State
import kotlinx.coroutines.launch

class MainViewModel(
  private val repository: HomeRepository
) : ViewModel() {


  init {
    getWeatherData()
  }

  private fun getWeatherData() {
  //   viewModelScope.launch {
  //
  //     repository.getHomeData().collect {
  //       when (it) {
  //         is ResponseResource.Error -> {
  //           Log.d("HomeViewModel", "Some error happened ${it.errorMessage}")
  //         }
  //
  //         is ResponseResource.Success -> {
  //           userDataList.postValue(
  //             State.success(
  //               msg = APP_SUCCESS_MESSAGE,
  //               data = null /*add your data*/
  //             )
  //           )
  //           Log.d("HomeViewModel", "${it.data}")
  //         }
  //       }
  //     }
  //   }
  }
}
