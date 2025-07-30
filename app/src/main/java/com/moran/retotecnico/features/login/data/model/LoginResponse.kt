package com.moran.retotecnico.features.login.data.model

import com.moran.retotecnico.features.login.domain.model.LoginUi

data class LoginResponse(
    val name: String?,
    val lastName: String?
)


fun LoginResponse?.toModel() = LoginUi(
    nameUser = this?.name.orEmpty(),
    lastNameUser = this?.lastName.orEmpty()
)