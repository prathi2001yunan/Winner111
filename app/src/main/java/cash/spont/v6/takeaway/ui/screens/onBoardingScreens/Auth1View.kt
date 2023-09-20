package cash.spont.v6.takeaway.ui.screens.onBoardingScreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cash.spont.v6.takeaway.manager.DataStoreHelper
import cash.spont.v6.tvtakeaway.R.drawable
import cash.spont.v6.takeaway.ui.theme.appTheme
import cash.spont.v6.takeaway.ui.theme.navigationBarPadding
import cash.spont.v6.takeaway.ui.theme.statusBarPadding
import cash.spont.v6.takeaway.ui.viewmodel.OnboardingScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val data = mutableStateOf("")

@Composable
fun Auth1View(
    onboardingScreenViewModel: OnboardingScreenViewModel,
    naveController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appTheme {})
            .padding(
                top = MaterialTheme.statusBarPadding(),
                bottom = MaterialTheme.navigationBarPadding()
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth(0.6f),
            shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
            colors = if (isSystemInDarkTheme()) {
                CardDefaults.cardColors(Color(0xFF212127))
            } else {
                CardDefaults.cardColors(Color(0x63BBBBBD))
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    Arrangement.SpaceBetween,
                    Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                    Auth1Logo()
                    Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                    Auth1Description()
                }
                Text(text = data.value)
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Auth1Image(onboardingScreenViewModel, naveController)
            }
        }
    }
}

@Composable
private fun Auth1Image(
    onboardingScreenViewModel: OnboardingScreenViewModel,
    naveController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = drawable.authstep1),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Bottom,
            Alignment.CenterHorizontally
        ) {
            Auth1Button(onboardingScreenViewModel, naveController = naveController)
            Spacer(modifier = Modifier.fillMaxHeight(0.3f))
        }
    }
}

@Composable
private fun Auth1Logo() {
    val icon = if (isSystemInDarkTheme()) {
        drawable.spont_white
    } else {
        drawable.spont_dark
    }
    Icon(
        painter = painterResource(id = icon),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(
                if (isSystemInDarkTheme()) 28.dp else
                    40.dp
            )
            .padding(horizontal = 100.dp)
    )
}

@Composable
private fun Auth1Description() {
    Text(
        "Welkom,lets activate your new device by scanning your QR code",
        fontSize = 25.sp,
        textAlign = TextAlign.Center,
        lineHeight = 25.sp,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 100.dp)
    )
}

@Composable
private fun Auth1Button(
    onboardingScreenViewModel: OnboardingScreenViewModel,
    naveController: NavController
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dataStore = DataStoreHelper(context)
    Button(
        onClick = {
            onboardingScreenViewModel.getData(context)
            onboardingScreenViewModel.screenCheck.value =
                OnboardingScreenViewModel.AuthFlow.Auth2View
            onboardingScreenViewModel.accessToken.value = ""
        },
        modifier = Modifier
            .fillMaxWidth(0.30f)
            .fillMaxHeight(0.1f),
        colors = ButtonDefaults.buttonColors(Color(0xFF4353FF))
    ) {
        Text(
            text = "Scan and Activate", color = Color.White, fontSize = 22.sp,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}