package com.moran.retotecnico.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.moran.retotecnico.features.login.presentation.navigation.LoginNavigation
import com.moran.retotecnico.features.login.presentation.navigation.loginScreen
import com.moran.retotecnico.features.signup.presentation.navigation.SignUpNavigation
import com.moran.retotecnico.features.signup.presentation.navigation.signUpScreen

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(
        route = "/main",
        startDestination = SignUpNavigation.getRouteToNavigate(),
    ) {

        signUpScreen(
            onGoToLogin = {
                navController.navigate(LoginNavigation.getRouteToNavigate())
            },
            onGoToHome = {

            }
        )

        loginScreen(
            onGoToHome = {

            }, onBackPressed = {
                navController.popBackStack()
            }
        )
    }
}