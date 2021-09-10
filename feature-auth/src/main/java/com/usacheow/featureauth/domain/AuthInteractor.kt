package com.usacheow.featureauth.domain

import com.usacheow.coredata.network.Completable
import com.usacheow.coredata.network.Effect2
import com.usacheow.featureauth.data.AuthRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AuthInteractor @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend fun signInWithLoginAndPassword(login: String, password: String): Effect2<Completable> {
        return repository.signInWithLoginAndPassword(login, password).toCompletable()
    }

    suspend fun signUpWithLoginAndPassword(login: String, password: String): Effect2<Completable> {
        return repository.signUpWithLoginAndPassword(login, password).toCompletable()
    }

    suspend fun signInWithPhone(phone: String): Effect2<Completable> {
        return repository.signInWithPhone(phone)
    }

    suspend fun resendCode(phone: String): Effect2<Completable> {
        return repository.resendCode(phone)
    }

    suspend fun verifyPhone(phone: String, code: String): Effect2<Completable> {
        return repository.verifyPhone(phone, code).toCompletable()
    }
}