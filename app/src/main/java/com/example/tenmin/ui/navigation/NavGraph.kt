package com.example.tenmin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tenmin.ui.model.Zila
import com.example.tenmin.ui.screens.HomeScreen
import com.example.tenmin.ui.screens.SearchScreen

@Composable
fun NavGraph(navController: NavHostController, zilaList: List<Zila>) {

  NavHost(
    navController = navController,
    startDestination = NavRoute.Home.path
  ) {
    addHomeScreen(navController, this)

    addSearchScreen(navController, this, zilaList)
  }
}

private fun addHomeScreen(
  navController: NavHostController,
  navGraphBuilder: NavGraphBuilder
) {
  navGraphBuilder.composable(route = NavRoute.Home.path) {

    HomeScreen(
      navigateToSearch = {
        navController.navigate(NavRoute.Search.withArgs())
      }
    )
  }
}

private fun addSearchScreen(
  navController: NavHostController,
  navGraphBuilder: NavGraphBuilder,
  zilaList: List<Zila>
) {
  navGraphBuilder.composable(
    route = NavRoute.Search.withArgsFormat(),
    arguments = listOf()
  ) { navBackStackEntry ->

    SearchScreen(
      popBackStack = { navController.popBackStack() },
    )
  }
}
