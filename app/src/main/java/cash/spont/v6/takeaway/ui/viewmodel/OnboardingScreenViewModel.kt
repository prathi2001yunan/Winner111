package cash.spont.v6.takeaway.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cash.spont.v6.takeaway.manager.SessionManager
import cash.spont.v6.takeaway.ui.screens.onBoardingScreens.ErrorView
import cash.spont.v6.takeawayrepo.repository.AuthTokenResponse
import cash.spont.v6.terminal.auth0.Auth0Repository
import cash.spont.v6.terminal.auth0.AuthCodeResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class OnboardingScreenViewModel() :
    ViewModel() {

    private val _order =
        MutableStateFlow<MutableList<AuthCodeResponse>>(mutableListOf())
    var order = _order.asStateFlow()
    private val _order1 =
        MutableStateFlow<MutableList<AuthTokenResponse>>(mutableListOf())
    var order1 = _order1.asStateFlow()
    var errorCheck = mutableStateOf(false)
    var deviceCode = mutableStateOf("")
    var check = mutableStateOf(true)
    var accessToken = mutableStateOf("")
    var refreshToken = mutableStateOf("")
    var expiryIn = mutableStateOf(0)
    var isPollingContinue = mutableStateOf(true)
    var checkExpiry = mutableStateOf(0)
    var screenCheck = mutableStateOf(AuthFlow.Auth1View)


    enum class AuthFlow {
        Auth1View, Loading, Auth2View, ErrorView, MainScreen
    }

    fun getData(context: Context) {
        viewModelScope.launch {
            val service = Auth0Repository.create()
            val session = SessionManager(context)
            _order.value = service.getDeviceCode().toMutableList()
            _order.value.forEach { i ->
                checkExpiry.value = i.expires_in
                deviceCode.value = i.device_code.toString()
            }
            startPollingExpiryTimer(checkExpiry.value)
        }
    }

    fun startPolling(deviceCode: String, context: Context) {

        viewModelScope.launch {
            val session = SessionManager(context)
            val service = Auth0Repository.create()
            _order1.value = service.getDeviceToken(deviceCode).toMutableList()
            _order1.value.forEach { i ->
                accessToken.value = i.access_token
                refreshToken.value = i.refresh_token
                expiryIn.value = i.expires_in
            }
            AccessTokenExpiry(expiryIn.value, refreshToken.value, context)
            session.setAuth(accessToken.value, refreshToken.value, expiryIn.value)

            errorCheck.value = false
            delay(10000L)
            errorCheck.value = true
        }

    }

    private fun startPollingExpiryTimer(int: Int) {
        val expirationTimer = Timer()
        expirationTimer.schedule(
            object : TimerTask() {
                override fun run() {

                    expirationTimer.cancel()
                    viewModelScope.launch {
                        screenCheck.value = AuthFlow.ErrorView
                    }
                }
            },
            checkExpiry.value * 100L
        )

    }

    private fun AccessTokenExpiry(int: Int, refresh_token: String, context: Context) {
        val expirationTimer = Timer()
        expirationTimer.schedule(
            object : TimerTask() {
                override fun run() {
                    expirationTimer.cancel()
                    viewModelScope.launch {
                        val service = Auth0Repository.create()
                        val session = SessionManager(context = context)
                        _order1.value = service.getNewToken(refresh_token).toMutableList()
                        _order1.value.forEach { i ->
                            accessToken.value = i.access_token
                            refreshToken.value = i.refresh_token
                            expiryIn.value = i.expires_in
                        }
                        session?.setAuth(accessToken.value, refreshToken.value, expiryIn.value)
                    }
                }
            }, int * 100L
        )
    }

    fun initDitto(context: Context) {

    }

    fun afterLogin() {

    }

    fun loadCompany(companyId: String) {

    }

    fun startCompany(company: String) {
    }

    fun loadDevice(company: String) {
    }
}
