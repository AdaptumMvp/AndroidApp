package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.domain.useCase.CompleteStageUseCase
import ru.adaptum.adaptumandroid.domain.useCase.GetStagesUseCase
import ru.adaptum.adaptumandroid.presentation.model.StageListItem
import javax.inject.Inject

class StageFragmentViewModel
    @Inject
    constructor(
        private val getStagesUseCase: GetStagesUseCase,
        private val completeStageUseCase: CompleteStageUseCase,
    ) : ViewModel() {
        private var _stageDataState = MutableStateFlow<StageListItem?>(null)
        val stageDataState: StateFlow<StageListItem?>
            get() = _stageDataState.asStateFlow()

        fun init(stageListItem: StageListItem) {
            viewModelScope.launch {
                _stageDataState.emit(stageListItem)
            }
        }

        fun onClickAccept(timeSpent: Int) {
            viewModelScope.launch {
                val userDataOnStageKeys = _stageDataState.value?.userDataOnStageKeys
                userDataOnStageKeys?.let { completeStageUseCase.invoke(timeSpent, it) }
            }
        }
    }
