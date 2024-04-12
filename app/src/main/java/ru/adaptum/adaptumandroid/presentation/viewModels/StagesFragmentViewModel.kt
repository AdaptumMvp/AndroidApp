package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.domain.useCase.GetStagesUseCase
import ru.adaptum.adaptumandroid.presentation.model.StageListItem
import javax.inject.Inject

class StagesFragmentViewModel
    @Inject
    constructor(
        private val getStagesUseCase: GetStagesUseCase,
    ) : ViewModel() {
        private val _stagesState = MutableSharedFlow<List<StageListItem>>()
        val stagesState: SharedFlow<List<StageListItem>>
            get() = _stagesState

        fun init(groupId: Int) {
            viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable -> throwable.printStackTrace() }) {
                _stagesState.emit(getStagesUseCase(groupId).map { StageListItem.fromModel(it) })
            }
        }
    }
