package ru.adaptum.adaptumandroid.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import ru.adaptum.adaptumandroid.AdaptumApp
import ru.adaptum.adaptumandroid.databinding.FragmentChatBinding
import ru.adaptum.adaptumandroid.domain.entity.MentorInfo
import ru.adaptum.adaptumandroid.presentation.adapters.MessageListAdapter
import ru.adaptum.adaptumandroid.presentation.common.collectFlow
import ru.adaptum.adaptumandroid.presentation.viewModels.ChatFragmentViewModel
import javax.inject.Inject

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private lateinit var messageListAdapter: MessageListAdapter
    private var mentor: MentorInfo? = null

    @Inject
    lateinit var viewModel: ChatFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as AdaptumApp).appComponent.inject(this)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViewModel()
        mentor?.let { viewModel.getMessages(it.id) }
    }

    private fun parseArgs() {
        mentor =
            arguments?.let {
                Gson().fromJson(
                    it.getString(MENTOR_INFO_ARG),
                    MentorInfo::class.java,
                )
            }
    }

    private fun initViews() {
        mentor?.let { mentor ->
            binding.mentorNameTv.text = mentor.fullName
            messageListAdapter = MessageListAdapter()
            binding.chatRecyclerView.adapter = messageListAdapter
            binding.sendButton.setOnClickListener {
                val text = binding.inputEditText.text.toString()
                Log.d("text", text)
                if (text.isBlank()) return@setOnClickListener
                viewModel.onClickSendMessage(text, mentor.id)
            }
        }
    }

    private fun bindViewModel() {
        collectFlow(viewModel.messagesState) {
            messageListAdapter.messageListItemList = it
            binding.inputEditText.text.clear()
        }
    }

    companion object {
        private const val MENTOR_INFO_ARG = "MENTOR_INFO"

        fun getInstance(mentorInfo: MentorInfo) =
            ChatFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(MENTOR_INFO_ARG, Gson().toJson(mentorInfo))
                    }
            }
    }
}
