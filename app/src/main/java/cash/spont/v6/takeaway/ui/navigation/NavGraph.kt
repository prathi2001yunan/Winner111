package cash.spont.v6.takeaway.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cash.spont.v6.takeaway.ui.screens.mainScreens.MainScreen
import cash.spont.v6.takeaway.ui.screens.onBoardingScreens.Auth1View
import cash.spont.v6.takeaway.ui.screens.onBoardingScreens.Auth2View
import cash.spont.v6.takeaway.ui.screens.onBoardingScreens.ErrorView
import cash.spont.v6.takeaway.ui.screens.onBoardingScreens.LoadingView
import cash.spont.v6.takeaway.ui.viewmodel.OnboardingScreenViewModel

@Composable
fun NavGraph(
    onboardingScreenViewModel: OnboardingScreenViewModel,
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = NavRoute.authScreen1.route) {
        composable(route = NavRoute.authScreen1.route) {
            Auth1View(onboardingScreenViewModel, navController)
        }
        composable(route = NavRoute.authScreen2.route) {
            Auth2View(onboardingScreenViewModel, navController)
        }
        composable(route = NavRoute.loadingScreen.route) {
            LoadingView(navController, onboardingScreenViewModel)
        }
        composable(route = NavRoute.mainScreen.route) {
            MainScreen(navController, onboardingScreenViewModel)
        }
        composable(route = NavRoute.errorScreen.route) {
            ErrorView(viewModel = onboardingScreenViewModel, naveController = navController)
        }
    }
}
