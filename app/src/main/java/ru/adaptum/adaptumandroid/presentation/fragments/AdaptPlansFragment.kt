package ru.adaptum.adaptumandroid.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.adaptum.adaptumandroid.AdaptumApp
import ru.adaptum.adaptumandroid.databinding.FragmentAdaptPlansBinding
import ru.adaptum.adaptumandroid.presentation.adapters.AdaptPlansListAdapter
import ru.adaptum.adaptumandroid.presentation.common.Navigator
import ru.adaptum.adaptumandroid.presentation.common.collectFlow
import ru.adaptum.adaptumandroid.presentation.viewModels.AdaptPlansFragmentViewModel
import javax.inject.Inject

class AdaptPlansFragment : Fragment() {
    private lateinit var binding: FragmentAdaptPlansBinding

    private lateinit var adaptPlansListAdapter: AdaptPlansListAdapter

    @Inject
    lateinit var viewModel: AdaptPlansFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as AdaptumApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAdaptPlansBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        bindViewModel()
        viewModel.init()
    }

    private fun initRecyclerView() {
        adaptPlansListAdapter = AdaptPlansListAdapter()
        binding.tasksRv.adapter = adaptPlansListAdapter
        adaptPlansListAdapter.onClickAction = {
            Navigator.navigateReplaceSaveStack(
                StagesFragment.newInstance(it),
                parentFragmentManager,
            )
        }
        adaptPlansListAdapter.onClickSendMessageAction = {
            Navigator.navigateReplaceSaveStack(ChatFragment.getInstance(it), parentFragmentManager)
        }
    }

    private fun bindViewModel() {
        collectFlow(viewModel.adaptListState) {
            adaptPlansListAdapter.submitList(it)
        }
    }
}
