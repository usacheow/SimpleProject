package com.usacheow.featureauth.domain

import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.featureauth.data.AuthRepository
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit

class AuthInteractorTest {

    @Rule @JvmField val rule = MockitoJUnit.rule()
//    @Rule @JvmField val rxRule = RxRule()

    private val repository = mock(AuthRepository::class.java)
    private val storage = mock(SettingsStorage::class.java)
    private val interactor = AuthInteractor(repository)

    @Test
    fun `should return complete on success response`() {
//        //given
//        val login = "login"
//        val password = "password"
//        val token = "token"
//        val observer = TestObserver<Unit>()
//        val response = Single.just(AccessToken(token))
//        `when`(repository.signInWithLoginAndPassword(login, password)).thenReturn(response)
//        //when
//        interactor.signInWithLoginAndPassword(login, password).subscribe(observer)
//        //then
//        verify(repository).signInWithLoginAndPassword(login, password)
//        observer.assertComplete()
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