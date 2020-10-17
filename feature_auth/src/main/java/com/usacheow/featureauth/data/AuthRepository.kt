package com.usacheow.featureauth.data

import com.usacheow.coredata.network.Completable
import com.usacheow.coredata.network.Effect
import com.usacheow.featureauth.data.models.AccessToken

interface AuthRepository {

    suspend fun signInWithLoginAndPassword(login: String, password: String): Effect<AccessToken>

    suspend fun signUpWithLoginAndPassword(login: String, password: String): Effect<AccessToken>

    suspend fun signInWithPhone(phone: String): Effect<Completable>

    suspend fun resendCode(phone: String): Effect<Completable>

    suspend fun verifyPhone(phone: String, code: String): Effect<AccessToken>
}