package com.example.tenmin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tenmin.ui.screens.AlbumListViewScreen

@Composable
fun NavGraph(
  navController: NavHostController
) {

  NavHost(
    navController = navController,
    startDestination = NavRoute.Album.path
  ) {
    addSearchScreen(navController, this)
  }
}

private fun addSearchScreen(
  navController: NavHostController,
  navGraphBuilder: NavGraphBuilder
) {
  navGraphBuilder.composable(
    route = NavRoute.Album.withArgsFormat(),
    arguments = listOf()
  ) { navBackStackEntry ->

    AlbumListViewScreen(popBackStack = { navController.popBackStack() })
  }
}
