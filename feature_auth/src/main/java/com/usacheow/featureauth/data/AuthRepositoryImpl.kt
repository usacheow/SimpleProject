package com.usacheow.featureauth.data

import com.usacheow.coredata.database.Storage
import com.usacheow.coredata.network.Completable
import com.usacheow.coredata.network.Effect
import com.usacheow.coredata.network.doOnSuccess
import com.usacheow.featureauth.data.models.AccessToken
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val storage: Storage
) : AuthRepository {

    override suspend fun signInWithLoginAndPassword(login: String, password: String): Effect<AccessToken> {
        delay(2000)
//        apiCall
        return Effect.Success(AccessToken("token")).doOnSuccess {
            storage.token = data.token
        }
    }

    override suspend fun signUpWithLoginAndPassword(login: String, password: String): Effect<AccessToken> {
        delay(2000)
//        apiCall
        return Effect.Success(AccessToken("token")).doOnSuccess {
            storage.token = data.token
        }
    }

    override suspend fun signInWithPhone(phone: String): Effect<Completable> {
        delay(2000)
        return Effect.Success(Completable)
    }

    override suspend fun resendCode(phone: String): Effect<Completable> {
        delay(2000)
        return Effect.Success(Completable)
    }

    override suspend fun verifyPhone(phone: String, code: String): Effect<AccessToken> {
        delay(2000)
//        apiCall
        return Effect.Success(AccessToken("token")).doOnSuccess {
            storage.token = data.token
            storage.phoneNumber = phone
        }
    }
}