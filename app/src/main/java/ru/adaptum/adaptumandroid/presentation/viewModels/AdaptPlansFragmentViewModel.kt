package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.domain.useCase.GetAdaptPlansUseCase
import ru.adaptum.adaptumandroid.presentation.model.AdaptPlanListItem
import java.lang.Exception
import javax.inject.Inject

class AdaptPlansFragmentViewModel
    @Inject
    constructor(
        private val getAdaptPlansUseCase: GetAdaptPlansUseCase,
    ) : ViewModel() {
        private var _adaptListState = MutableSharedFlow<List<AdaptPlanListItem>>()
        val adaptListState: SharedFlow<List<AdaptPlanListItem>>
            get() = _adaptListState.asSharedFlow()

        fun init() {
            viewModelScope.launch {
                try {
                    val plans = getAdaptPlansUseCase().map { AdaptPlanListItem.fromModel(it) }
                    _adaptListState.emit(plans)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
