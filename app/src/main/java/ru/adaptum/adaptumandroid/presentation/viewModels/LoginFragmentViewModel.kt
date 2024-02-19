package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.domain.entity.State
import ru.adaptum.adaptumandroid.domain.entity.User
import ru.adaptum.adaptumandroid.domain.useCase.LoginUseCase
import javax.inject.Inject

class LoginFragmentViewModel
    @Inject
    constructor(
        private var loginUseCase: LoginUseCase,
    ) : ViewModel() {
        private val _loginFragmentState = MutableStateFlow(LoginFragmentState.initial())
        val loginFragmentState: StateFlow<LoginFragmentState>
            get() = _loginFragmentState

        fun onClickAuth(
            username: String?,
            password: String?,
        ) {
            viewModelScope.launch {
                val previousValue = _loginFragmentState.value
                if (username.isNullOrBlank() || password.isNullOrBlank()) {
                    _loginFragmentState.emit(previousValue.copy(error = ERROR_FILL_FIELDS))
                    return@launch
                }
                loginUseCase.auth(User(username, password)).collectLatest {
                    when (it) {
                        is State.Loading ->
                            _loginFragmentState.emit(
                                previousValue.copy(
                                    loading = true,
                                    error = null,
                                ),
                            )

                        is State.Failure -> {
                            _loginFragmentState.emit(previousValue.copy(error = AUTH_ERROR))
                            it.msg.printStackTrace()
                        }
                        is State.Success ->
                            _loginFragmentState.emit(
                                previousValue.copy(
                                    success = true,
                                    error = null,
                                ),
                            )

                        is State.Empty -> {}
                    }
                }
            }
        }

        companion object {
            private const val ERROR_FILL_FIELDS = "Заполните все поля"
            private const val AUTH_ERROR = "Неудачная попытка авторизации. Повторите позднее."
        }
    }

data class LoginFragmentState(
    val loading: Boolean,
    val success: Boolean,
    val error: String?,
) {
    companion object {
        fun initial() =
            LoginFragmentState(
                loading = false,
                success = false,
                error = null,
            )
    }
}
