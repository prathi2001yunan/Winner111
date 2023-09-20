package cash.spont.v6.takeaway.ui.screens.onBoardingScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cash.spont.v6.takeaway.ui.theme.appTheme
import cash.spont.v6.takeaway.ui.theme.navigationBarPadding
import cash.spont.v6.takeaway.ui.theme.statusBarPadding
import cash.spont.v6.takeaway.ui.viewmodel.OnboardingScreenViewModel
import kotlinx.coroutines.delay

@Composable
fun LoadingView(
    naveController: NavController,
    onboardingScreenViewModel: OnboardingScreenViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appTheme {})
            .padding(
                top = MaterialTheme.statusBarPadding(),
                bottom = MaterialTheme.navigationBarPadding()
            ),
        Arrangement.Center, Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = Color(0xFF8642E4)
            )
        }
    }

}
