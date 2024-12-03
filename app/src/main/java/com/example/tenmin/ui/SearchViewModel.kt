package com.example.tenmin.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tenmin.R
import com.example.tenmin.ui.model.Zila
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.BufferedReader

class SearchViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {

  private val _zilaList = MutableStateFlow<List<Zila>>(emptyList())
  val zilaList: StateFlow<List<Zila>> = _zilaList

  // private val _searchQuery = MutableStateFlow("")
  // val searchQuery: StateFlow<String> = _searchQuery

  init {
    loadZilas()
  }

  private fun loadZilas() {
    viewModelScope.launch(Dispatchers.IO) {
      _zilaList.value = readZilasFromJson() ?: emptyList()
    }
  }

  private fun readZilasFromJson(): List<Zila>? {
    return try {
      val inputStream = mApplication.resources.openRawResource(R.raw.zila)
      val jsonString = inputStream.bufferedReader().use(BufferedReader::readText)
      val listType = object : TypeToken<List<Zila>>() {}.type
      Gson().fromJson(jsonString, listType)
    } catch (e: Exception) {
      println(e.message)
      null
    }
  }

  // fun onQueryChanged(query: String) {
  //   _searchQuery.value = query
  // }
}
