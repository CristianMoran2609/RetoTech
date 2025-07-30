package com.moran.retotecnico.features.signup.presentation.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moran.retotecnico.features.signup.presentation.SignUpScreen
import com.moran.retotecnico.features.signup.presentation.SignUpViewModel

class SignUpNavigation {
    companion object {
        const val route: String = "/signup"
        fun getRouteToNavigate() = route
    }
}

fun NavGraphBuilder.signUpScreen(
    onGoToLogin: () -> Unit,
    onGoToHome: () -> Unit
) {
    composable(SignUpNavigation.getRouteToNavigate()) {
        val viewModel: SignUpViewModel = viewModel()
        val state = viewModel.state.collectAsStateWithLifecycle().value
        val onEvent = viewModel::onEvent

        SignUpScreen(
            state = state,
            onEvent = onEvent,
            actionsFlow = viewModel.action,
            onGoToLogin = onGoToLogin,
            onGoToHome = onGoToHome
        )
    }
}