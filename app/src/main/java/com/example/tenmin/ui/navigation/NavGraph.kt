package com.example.tenmin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tenmin.ui.SharedViewModel
import com.example.tenmin.ui.model.Zila
import com.example.tenmin.ui.screens.HomeScreen
import com.example.tenmin.ui.screens.SearchScreen

@Composable
fun NavGraph(
  navController: NavHostController,
  zilaList: List<Zila>,
  sharedViewModel: SharedViewModel
) {

  NavHost(
    navController = navController,
    startDestination = NavRoute.Home.path
  ) {
    addHomeScreen(navController, this, sharedViewModel)

    addSearchScreen(navController, this, zilaList, sharedViewModel)
  }
}

private fun addHomeScreen(
  navController: NavHostController,
  navGraphBuilder: NavGraphBuilder,
  sharedViewModel: SharedViewModel
) {
  navGraphBuilder.composable(route = NavRoute.Home.path) {

    HomeScreen(
      navigateToSearch = {
        navController.navigate(NavRoute.Search.withArgs())
      },
      sharedViewModel
    )
  }
}

private fun addSearchScreen(
  navController: NavHostController,
  navGraphBuilder: NavGraphBuilder,
  zilaList: List<Zila>,
  sharedViewModel: SharedViewModel
) {
  navGraphBuilder.composable(
    route = NavRoute.Search.withArgsFormat(),
    arguments = listOf()
  ) { navBackStackEntry ->

    SearchScreen(
      popBackStack = { navController.popBackStack() },
      zilaList = zilaList,
      sharedViewModel
    )
  }
}
