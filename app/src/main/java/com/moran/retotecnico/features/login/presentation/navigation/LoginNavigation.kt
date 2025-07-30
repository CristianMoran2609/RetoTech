package com.moran.retotecnico.features.login.presentation.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moran.retotecnico.features.login.presentation.LoginScreen
import com.moran.retotecnico.features.login.presentation.LoginViewModel

class LoginNavigation {
    companion object {
        const val route: String = "/login"
        fun getRouteToNavigate() = route
    }
}

fun NavGraphBuilder.loginScreen(
    onGoToHome: () -> Unit,
    onBackPressed: () -> Unit
) {
    composable(LoginNavigation.getRouteToNavigate()) {
        val viewModel: LoginViewModel = viewModel()
        val state = viewModel.state.collectAsStateWithLifecycle().value
        val onEvent = viewModel::onEvent

        LoginScreen(
            state = state,
            onEvent = onEvent,
            actionsFlow = viewModel.action,
            onGoToHome = onGoToHome,
            onBackPressed = onBackPressed
        )
    }
}