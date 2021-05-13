package com.usacheow.featureauth.data

import com.usacheow.coredata.database.Storage
import com.usacheow.coredata.network.Completable
import com.usacheow.coredata.network.Effect
import com.usacheow.coredata.network.doOnSuccess
import com.usacheow.coreui.di.IoDispatcher
import com.usacheow.featureauth.data.models.AccessToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val storage: Storage,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun signInWithLoginAndPassword(login: String, password: String) = withContext(dispatcher) {
        delay(2000)
//        apiCall
        Effect.Success(AccessToken("token")).doOnSuccess {
            storage.token = data.token
        }
    }

    override suspend fun signUpWithLoginAndPassword(login: String, password: String) = withContext(dispatcher) {
        delay(2000)
//        apiCall
        Effect.Success(AccessToken("token")).doOnSuccess {
            storage.token = data.token
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
            storage.token = data.token
            storage.phoneNumber = phone
        }
    }
}