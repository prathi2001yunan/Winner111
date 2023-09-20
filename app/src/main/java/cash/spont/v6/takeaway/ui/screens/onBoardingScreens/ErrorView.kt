package cash.spont.v6.takeaway.ui.screens.onBoardingScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.accessibility.AccessibilityViewCommand.SetProgressArguments
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import cash.spont.v6.takeaway.ui.viewmodel.OnboardingScreenViewModel

@Composable
fun ErrorView(viewModel: OnboardingScreenViewModel, naveController: NavController) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Center, Alignment.CenterHorizontally
        ) {
            Text(text = "Authentication Expired", fontSize = 35.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    viewModel.screenCheck.value = OnboardingScreenViewModel.AuthFlow.Auth1View
                },
                Modifier
                    .width(150.dp)
                    .height(50.dp)
            ) {
                Text(text = "back", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
            }
        }
    }
}