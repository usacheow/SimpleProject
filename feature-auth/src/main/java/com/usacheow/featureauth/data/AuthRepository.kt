package com.usacheow.featureauth.data

import com.usacheow.coredata.coroutine.IoDispatcher
import com.usacheow.coredata.database.TokenStorage
import com.usacheow.coredata.database.UserDataStorage
import com.usacheow.coredata.network.Completable
import com.usacheow.coredata.network.Effect2
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AuthRepository {

    suspend fun signInWithLoginAndPassword(login: String, password: String): Effect2<AccessTokenDto>

    suspend fun signUpWithLoginAndPassword(login: String, password: String): Effect2<AccessTokenDto>

    suspend fun signInWithPhone(phone: String): Effect2<Completable>

    suspend fun resendCode(phone: String): Effect2<Completable>

    suspend fun verifyPhone(phone: String, code: String): Effect2<AccessTokenDto>
}

class AuthRepositoryImpl @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val userDataStorage: UserDataStorage,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun signInWithLoginAndPassword(login: String, password: String) = withContext(dispatcher) {
        delay(2000)
//        apiCall
        Effect2.success(AccessTokenDto("token")).doOnSuccess {
            tokenStorage.token = it.token
        }
    }

    override suspend fun signUpWithLoginAndPassword(login: String, password: String) = withContext(dispatcher) {
        delay(2000)
//        apiCall
        Effect2.success(AccessTokenDto("token")).doOnSuccess {
            tokenStorage.token = it.token
        }
    }

    override suspend fun signInWithPhone(phone: String) = withContext(dispatcher) {
        delay(2000)
        Effect2.success(Completable)
    }

    override suspend fun resendCode(phone: String) = withContext(dispatcher) {
        delay(2000)
        Effect2.success(Completable)
    }

    override suspend fun verifyPhone(phone: String, code: String) = withContext(dispatcher) {
        delay(2000)
//        apiCall
        Effect2.success(AccessTokenDto("token")).doOnSuccess {
            tokenStorage.token = it.token
            userDataStorage.setPhoneNumber(phone)
        }
    }
}