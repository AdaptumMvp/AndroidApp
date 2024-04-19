package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.domain.useCase.GetEventsUseCase
import ru.adaptum.adaptumandroid.domain.useCase.RegistrationToEventUseCase
import ru.adaptum.adaptumandroid.presentation.model.EventListItem
import javax.inject.Inject

class EventsFragmentViewModel
    @Inject
    constructor(
        private val getEventsUseCase: GetEventsUseCase,
        private val registrationToEventUseCase: RegistrationToEventUseCase,
    ) : ViewModel() {
        private val _eventsState = MutableStateFlow<List<EventListItem>?>(null)
        val eventsState: StateFlow<List<EventListItem>?>
            get() = _eventsState.asStateFlow()

        fun init() {
            viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable -> throwable.printStackTrace() }) {
                val list = getEventsUseCase().map { EventListItem.fromModel(it) }
                _eventsState.emit(list)
            }
        }

        fun onClickRegister(id: Int) {
            viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable -> throwable.printStackTrace() }) {
                registrationToEventUseCase.invoke(id)
            }
        }
    }
