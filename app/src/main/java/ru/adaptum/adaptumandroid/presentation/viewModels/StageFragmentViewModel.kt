package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.domain.useCase.CompleteStageUseCase
import ru.adaptum.adaptumandroid.presentation.model.StageListItem
import javax.inject.Inject

class StageFragmentViewModel
    @Inject
    constructor(
        private val completeStageUseCase: CompleteStageUseCase,
        private val stageListItem: StageListItem,
    ) : ViewModel() {
        private var _stageDataState = MutableStateFlow<StageListItem?>(null)
        val stageDataState: StateFlow<StageListItem?>
            get() = _stageDataState.asStateFlow()

        fun init() {
            _stageDataState.value = stageListItem
        }

        fun onClickAccept(timeSpent: Int) {
            viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable -> throwable.printStackTrace() }) {
                completeStageUseCase(timeSpent, stageListItem.userDataOnStageKeys)
            }
        }
    }
