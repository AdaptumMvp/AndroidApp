package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.presentation.common.tracker.TimeTracker
import javax.inject.Inject
import kotlin.math.roundToInt

class TrackerFragmentViewModel
    @Inject
    constructor(
        private val timeTracker: TimeTracker,
    ) : ViewModel() {
        private val _timeCountState = MutableStateFlow<Long>(0)
        val timeCountState: StateFlow<Long>
            get() = _timeCountState

        private val _closeScreenCommand = MutableStateFlow<String?>(null)
        val closeScreenCommand: StateFlow<String?>
            get() = _closeScreenCommand.asStateFlow()

        private var _isStarted: Boolean = false
        val isStarted: Boolean
            get() = _isStarted

        fun init() {
            viewModelScope.launch {
                timeTracker.listen().collectLatest {
                    _timeCountState.emit(it)
                }
            }
        }

        fun onClickStop() {
            viewModelScope.launch {
                _closeScreenCommand.emit(
                    (((timeTracker.getActualValue() / 3600.0) * 1000).roundToInt() / 1000.0).toString()
                        .plus("ч."),
                )
            }
        }

        fun onClickPauseResume() {
            _isStarted =
                if (timeTracker.isRunning()) {
                    timeTracker.pause()
                    false
                } else {
                    timeTracker.resume()
                    true
                }
        }
    }
