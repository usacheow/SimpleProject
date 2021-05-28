package com.usacheow.featureauth.data

import com.usacheow.coredata.coroutine.IoDispatcher
import com.usacheow.coredata.database.TokenStorage
import com.usacheow.coredata.database.UserDataStorage
import com.usacheow.coredata.network.Completable
import com.usacheow.coredata.network.Effect
import com.usacheow.coredata.network.doOnSuccess
import com.usacheow.featureauth.data.models.AccessToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AuthRepository {

    suspend fun signInWithLoginAndPassword(login: String, password: String): Effect<AccessToken>

    suspend fun signUpWithLoginAndPassword(login: String, password: String): Effect<AccessToken>

    suspend fun signInWithPhone(phone: String): Effect<Completable>

    suspend fun resendCode(phone: String): Effect<Completable>

    suspend fun verifyPhone(phone: String, code: String): Effect<AccessToken>
}

class AuthRepositoryImpl @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val userDataStorage: UserDataStorage,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun signInWithLoginAndPassword(login: String, password: String) = withContext(dispatcher) {
        delay(2000)
//        apiCall
        Effect.Success(AccessToken("token")).doOnSuccess {
            tokenStorage.token = data.token
        }
    }

    override suspend fun signUpWithLoginAndPassword(login: String, password: String) = withContext(dispatcher) {
        delay(2000)
//        apiCall
        Effect.Success(AccessToken("token")).doOnSuccess {
            tokenStorage.token = data.token
        }
    }

    override suspend fun signInWithPhone(phone: String) = withContext(dispatcher) {
        delay(2000)
        Effect.Success(Completable)
    }

    override suspend fun resendCode(phone: String) = withContext(dispatcher) {
        delay(2000)
        Effect.Success(Completable)
    }

    override suspend fun verifyPhone(phone: String, code: String) = withContext(dispatcher) {
        delay(2000)
//        apiCall
        Effect.Success(AccessToken("token")).doOnSuccess {
            tokenStorage.token = data.token
            userDataStorage.setPhoneNumber(phone)
        }
    }
}