package com.example.tenmin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.simplenavigationcompose.R

@Composable
fun HomeScreen(navigateToSearch: () -> Unit) {

  SearchTopBar(onSearchClick = navigateToSearch)

  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    WeatherHomeScreen(
      location = "Dhaka City",
      temperature = "22°C",
      weatherDescription = "Partly Cloudy",
      weatherIcon = R.drawable.ic_weather, // Replace with your drawable resource
      humidity = "65%",
      windSpeed = "15 km/h"
    )
  }
}

@Composable
fun SearchTopBar(onSearchClick: () -> Unit) {
  TopAppBar(
    title = { Text(text = "Weather App") },
    navigationIcon = {
      IconButton(onClick = onSearchClick) {
        Icon(
          imageVector = Icons.Default.Search,
          contentDescription = "Search Icon"
        )
      }
    },
    backgroundColor = MaterialTheme.colors.primary,
    contentColor = MaterialTheme.colors.onPrimary,
    elevation = 4.dp
  )
}

@Composable
fun WeatherHomeScreen(
  location: String,
  temperature: String,
  weatherDescription: String,
  weatherIcon: Int, // Drawable resource ID for the weather icon
  humidity: String,
  windSpeed: String
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Location Text
    Text(
      text = location,
      style = MaterialTheme.typography.h4,
      color = MaterialTheme.colors.primary
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Weather Icon
    Image(
      painter = painterResource(id = weatherIcon),
      contentDescription = "Weather Icon",
      modifier = Modifier.size(100.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Temperature
    Text(
      text = temperature,
      style = MaterialTheme.typography.h2,
      color = MaterialTheme.colors.onBackground
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Weather Description
    Text(
      text = weatherDescription,
      style = MaterialTheme.typography.subtitle1,
      color = MaterialTheme.colors.onBackground
    )

    Spacer(modifier = Modifier.height(32.dp))

    // Additional Weather Info
    Row(
      horizontalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Humidity", style = MaterialTheme.typography.caption)
        Text(text = humidity, style = MaterialTheme.typography.body1)
      }
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Wind Speed", style = MaterialTheme.typography.caption)
        Text(text = windSpeed, style = MaterialTheme.typography.body1)
      }
    }
  }
}
