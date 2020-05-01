package com.usacheow.featureauth.domain

import com.usacheow.coredata.database.Storage
import com.usacheow.coredata.network.BaseInteractor
import com.usacheow.coredata.network.setRequestThreads
import com.usacheow.featureauth.data.AuthRepository
import io.reactivex.CompletableObserver
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class AuthInteractor
@Inject constructor(
    private val repository: AuthRepository,
    private val storage: Storage
) : BaseInteractor() {

    fun signInWithLoginAndPassword(login: String, password: String, observer: CompletableObserver) {
        repository.signInWithLoginAndPassword(login, password)
            .doOnSubscribe { disposables += it }
            .setRequestThreads()
            .doOnSuccess { storage.token = it.token }
            .ignoreElement()
            .subscribe(observer)
    }

    fun signUpWithLoginAndPassword(login: String, password: String, observer: CompletableObserver) {
        repository.signUpWithLoginAndPassword(login, password)
            .doOnSubscribe { disposables += it }
            .setRequestThreads()
            .doOnSuccess { storage.token = it.token }
            .ignoreElement()
            .subscribe(observer)
    }

    fun signInWithPhone(phone: String, observer: CompletableObserver) {
        repository.signInWithPhone(phone)
            .doOnSubscribe { disposables += it }
            .setRequestThreads()
            .subscribe(observer)
    }

    fun resendCode(phone: String, observer: CompletableObserver) {
        repository.resendCode(phone)
            .doOnSubscribe { disposables += it }
            .setRequestThreads()
            .subscribe(observer)
    }

    fun verifyPhone(phone: String, code: String, observer: CompletableObserver) {
        repository.verifyPhone(phone, code)
            .doOnSubscribe { disposables += it }
            .setRequestThreads()
            .doOnSuccess { storage.phoneNumber = phone }
            .doOnSuccess { storage.token = it.token }
            .ignoreElement()
            .subscribe(observer)
    }
}