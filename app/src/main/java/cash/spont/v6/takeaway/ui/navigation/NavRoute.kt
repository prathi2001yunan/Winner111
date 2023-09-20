package cash.spont.v6.takeaway.ui.navigation

sealed class NavRoute(val route: String) {
    object authScreen1 : NavRoute("screen_1")
    object authScreen2 : NavRoute("screen_2")
    object loadingScreen : NavRoute("screen_3")
    object mainScreen : NavRoute("screen_4")
    object errorScreen : NavRoute("screen_5")
}
