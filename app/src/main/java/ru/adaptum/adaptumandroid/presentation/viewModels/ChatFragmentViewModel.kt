package ru.adaptum.adaptumandroid.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.domain.entity.MessageBody
import ru.adaptum.adaptumandroid.domain.useCase.GetMessagesUseCase
import ru.adaptum.adaptumandroid.domain.useCase.GetProfileDataUseCase
import ru.adaptum.adaptumandroid.domain.useCase.SendMessageUseCase
import ru.adaptum.adaptumandroid.presentation.model.MessageListItem
import javax.inject.Inject

class ChatFragmentViewModel
    @Inject
    constructor(
        private val getMessagesUseCase: GetMessagesUseCase,
        private val sendMessageUseCase: SendMessageUseCase,
        private val getProfileDataUseCase: GetProfileDataUseCase,
    ) : ViewModel() {
        private val _messagesState = MutableStateFlow<List<MessageListItem>>(listOf())
        val messagesState: StateFlow<List<MessageListItem>>
            get() = _messagesState

        fun getMessages(contactId: Int) {
            viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable -> throwable.printStackTrace() }) {
                contactId.let {
                    val messages = getMessagesUseCase.invoke(it)
                    _messagesState.value = messages.map { MessageListItem.fromModel(it) }
                }
            }
        }

        fun onClickSendMessage(
            messageText: String,
            contactId: Int,
        ) {
            viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable -> throwable.printStackTrace() }) {
                val userId = getProfileDataUseCase.invoke().id
                val message = MessageBody(userId, contactId, messageText)
                sendMessageUseCase(message)
                getMessages(contactId)
            }
        }
    }
