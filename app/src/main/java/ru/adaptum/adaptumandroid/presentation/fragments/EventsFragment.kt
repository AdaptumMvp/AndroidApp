package ru.adaptum.adaptumandroid.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import ru.adaptum.adaptumandroid.AdaptumApp
import ru.adaptum.adaptumandroid.databinding.FragmentEventsBinding
import ru.adaptum.adaptumandroid.presentation.adapters.EventsListAdapter
import ru.adaptum.adaptumandroid.presentation.viewModels.EventsFragmentViewModel
import javax.inject.Inject

class EventsFragment : Fragment() {
    private lateinit var binding: FragmentEventsBinding

    private lateinit var eventsAdapter: EventsListAdapter

    @Inject
    lateinit var viewModel: EventsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as AdaptumApp).appComponent.inject(this)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViewModel()
        viewModel.init()
    }

    private fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        eventsAdapter = EventsListAdapter()
        eventsAdapter.registerAction = {
            viewModel.onClickRegister(it)
        }
        binding.eventsRv.adapter = eventsAdapter
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.eventsState.flowWithLifecycle(
                lifecycle,
                Lifecycle.State.RESUMED,
            ).filterNotNull()
                .collectLatest {
                    eventsAdapter.submitList(it)
                }
        }
    }
}
