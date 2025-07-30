package com.moran.retotecnico.features.signup.data.model

import com.moran.retotecnico.features.login.domain.model.LoginUi

data class SignUpResponse(
    val email: String?,
    val password: String?
)


//fun SignUpResponse?.toModel() = LoginUi(
//    nameUser = this?.name.orEmpty(),
//    lastNameUser = this?.lastName.orEmpty()
//)