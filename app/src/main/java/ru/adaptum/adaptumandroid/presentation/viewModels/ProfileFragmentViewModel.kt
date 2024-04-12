package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.domain.entity.State
import ru.adaptum.adaptumandroid.domain.useCase.GetProfileDataUseCase
import ru.adaptum.adaptumandroid.domain.useCase.LogoutUseCase
import ru.adaptum.adaptumandroid.presentation.model.ProfileDataUI
import javax.inject.Inject

class ProfileFragmentViewModel
    @Inject
    constructor(
        private val getProfileDataUseCase: GetProfileDataUseCase,
        private val logoutUseCase: LogoutUseCase,
    ) : ViewModel() {
        private var _profileDataState = MutableStateFlow<ProfileDataUI?>(null)
        val profileDataState: StateFlow<ProfileDataUI?>
            get() = _profileDataState.asStateFlow()

        private val _logoutCommand = MutableSharedFlow<Unit?>()
        val logoutCommand: SharedFlow<Unit?>
            get() = _logoutCommand.asSharedFlow()

        fun init() {
            viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable -> throwable.printStackTrace() }) {
                _profileDataState.emit(ProfileDataUI.fromProfileData(getProfileDataUseCase()))
            }
        }

        fun logout() {
            viewModelScope.launch {
                logoutUseCase().collectLatest {
                    when (it) {
                        is State.Loading -> {}
                        is State.Success -> {
                            _logoutCommand.emit(Unit)
                        }

                        is State.Failure -> {
                        }

                        is State.Empty -> {}
                    }
                }
            }
        }
    }
