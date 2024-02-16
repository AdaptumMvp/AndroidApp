package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.domain.entity.User
import ru.adaptum.adaptumandroid.domain.useCase.LoginUseCase
import javax.inject.Inject

class LoginFragmentViewModel
    @Inject
    constructor(
        private var loginUseCase: LoginUseCase,
    ) : ViewModel() {
        private val _authStatusState = MutableSharedFlow<Boolean>()
        val authStatusState: SharedFlow<Boolean>
            get() = _authStatusState.asSharedFlow()

        fun onClickAuth(
            username: String?,
            password: String?,
        ) {
            viewModelScope.launch {
                if (username.isNullOrBlank() || password.isNullOrBlank()) {
                    _authStatusState.emit(false)
                } else {
                    loginUseCase.auth(User(username, password)).collectLatest {
                        _authStatusState.emit(it.token.isNotBlank())
                    }
                }
            }
        }
    }
