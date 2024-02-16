package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.domain.useCase.GetEventsUseCase
import ru.adaptum.adaptumandroid.presentation.model.EventListItem
import java.lang.Exception
import javax.inject.Inject

class EventsFragmentViewModel
    @Inject
    constructor(
        private val getEventsUseCase: GetEventsUseCase,
    ) : ViewModel() {
        private val _eventsState = MutableStateFlow<List<EventListItem>?>(null)
        val eventsState: StateFlow<List<EventListItem>?>
            get() = _eventsState.asStateFlow()

        fun init() {
            viewModelScope.launch {
                try {
                    val list = getEventsUseCase().map { EventListItem.fromModel(it) }
                    _eventsState.emit(list)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun onClickRegister(id: Int) {
        }
    }
