package cash.spont.v6.takeaway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cash.spont.v6.takeaway.manager.DataStoreHelper
import cash.spont.v6.takeaway.ui.navigation.NavGraph
import cash.spont.v6.takeaway.ui.screens.mainScreens.MainScreen
import cash.spont.v6.takeaway.ui.screens.onBoardingScreens.Auth1View
import cash.spont.v6.takeaway.ui.screens.onBoardingScreens.Auth2View
import cash.spont.v6.takeaway.ui.screens.onBoardingScreens.ErrorView
import cash.spont.v6.takeaway.ui.screens.onBoardingScreens.LoadingView
import cash.spont.v6.takeaway.ui.theme.TvTakeAwayTheme
import cash.spont.v6.takeaway.ui.viewmodel.OnboardingScreenViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private lateinit var naveController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val view by viewModels<OnboardingScreenViewModel>()
            TvTakeAwayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LaunchedEffect(key1 = true) {
                        delay(5000L)
                        view.errorCheck.value = true
                    }
                    naveController = rememberNavController()
                    val datastore = DataStoreHelper(LocalContext.current)
                    val datam by datastore.getAccessToken.collectAsState(initial = "")

                    Crossfade(
                        targetState = if (datam!!.isEmpty()) view.screenCheck.value
                        else {
                            if (view.errorCheck.value) OnboardingScreenViewModel.AuthFlow.MainScreen
                            else OnboardingScreenViewModel.AuthFlow.Loading
                        }
                    ) { screen ->
                        when (screen) {
                            OnboardingScreenViewModel.AuthFlow.Auth1View -> Auth1View(
                                onboardingScreenViewModel = view,
                                naveController = naveController
                            )

                            OnboardingScreenViewModel.AuthFlow.Auth2View -> Auth2View(
                                onboardingScreenViewModel = view,
                                naveController = naveController
                            )

                            OnboardingScreenViewModel.AuthFlow.ErrorView -> ErrorView(
                                view, naveController
                            )

                            OnboardingScreenViewModel.AuthFlow.MainScreen -> MainScreen(
                                naveController, view
                            )

                            else -> {
                                LoadingView(naveController = naveController, view)
                            }
                        }

                    }
                }
            }
        }
    }
}

