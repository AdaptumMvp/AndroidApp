package ru.adaptum.adaptumandroid.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.adaptum.adaptumandroid.AdaptumApp
import ru.adaptum.adaptumandroid.databinding.FragmentStagesBinding
import ru.adaptum.adaptumandroid.presentation.adapters.StagesListAdapter
import ru.adaptum.adaptumandroid.presentation.common.Navigator
import ru.adaptum.adaptumandroid.presentation.common.collectFlow
import ru.adaptum.adaptumandroid.presentation.viewModels.StagesFragmentViewModel
import javax.inject.Inject

class StagesFragment : Fragment() {
    private lateinit var binding: FragmentStagesBinding
    private lateinit var stagesListAdapter: StagesListAdapter

    @Inject
    lateinit var viewModel: StagesFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as AdaptumApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViewModel()
        arguments?.getInt(GROUP_ID)?.let { viewModel.init(it) }
    }

    private fun bindViewModel() {
        collectFlow(viewModel.stagesState) {
            stagesListAdapter.submitList(it)
        }
    }

    private fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        stagesListAdapter = StagesListAdapter()
        with(binding.tasksRv) {
            adapter = stagesListAdapter
        }
        stagesListAdapter.onClickStage = {
            Navigator.navigateReplaceSaveStack(StageFragment.getInstance(it), parentFragmentManager)
        }
    }

    companion object {
        private const val GROUP_ID = "GROUP_ID"

        fun newInstance(groupId: Int) =
            StagesFragment().apply {
                arguments =
                    Bundle().apply {
                        putInt(GROUP_ID, groupId)
                    }
            }
    }
}
