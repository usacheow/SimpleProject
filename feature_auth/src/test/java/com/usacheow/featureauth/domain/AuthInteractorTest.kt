package com.usacheow.featureauth.domain

import com.usacheow.coredata.database.Storage
import com.usacheow.coreunittest.RxRule
import com.usacheow.featureauth.data.AuthRepository
import com.usacheow.featureauth.data.models.AccessToken
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit

class AuthInteractorTest {

    @Rule @JvmField val rule = MockitoJUnit.rule()
    @Rule @JvmField val rxRule = RxRule()

    private val repository = mock(AuthRepository::class.java)
    private val storage = mock(Storage::class.java)
    private val interactor = AuthInteractor(repository, storage)

    @Test
    fun `should return complete on success response`() {
        //given
        val login = "login"
        val password = "password"
        val token = "token"
        val observer = TestObserver<Unit>()
        val response = Single.just(AccessToken(token))
        `when`(repository.signInWithLoginAndPassword(login, password)).thenReturn(response)
        //when
        interactor.signInWithLoginAndPassword(login, password, observer)
        //then
        verify(repository).signInWithLoginAndPassword(login, password)
        verify(storage).token = token
        observer.assertComplete()
    }

    @Test
    fun signUpWithLoginAndPassword() {
    }

    @Test
    fun signInWithPhone() {
    }

    @Test
    fun resendCode() {
    }

    @Test
    fun verifyPhone() {
    }
}