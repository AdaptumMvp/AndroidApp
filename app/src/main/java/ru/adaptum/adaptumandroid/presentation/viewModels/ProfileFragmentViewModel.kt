package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

        fun init() {
            viewModelScope.launch {
                try {
                    val profileData = getProfileDataUseCase()
                    _profileDataState.emit(ProfileDataUI.fromProfileData(profileData))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun logout() {
            logoutUseCase.invoke()
        }
    }
