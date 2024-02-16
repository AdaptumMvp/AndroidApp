package ru.adaptum.adaptumandroid.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import ru.adaptum.adaptumandroid.AdaptumApp
import ru.adaptum.adaptumandroid.R
import ru.adaptum.adaptumandroid.databinding.FragmentTrackerBinding
import ru.adaptum.adaptumandroid.presentation.common.Navigator
import ru.adaptum.adaptumandroid.presentation.common.collectFlow
import ru.adaptum.adaptumandroid.presentation.viewModels.TrackerFragmentViewModel
import javax.inject.Inject

class TrackerFragment : Fragment() {
    private lateinit var binding: FragmentTrackerBinding

    @Inject
    lateinit var viewModel: TrackerFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTrackerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as AdaptumApp).appComponent.inject(this)
        initViews()
        bindViewModel()
        viewModel.init()
    }

    private fun initViews() {
        initPauseButton()
        initStopButton()
    }

    private fun initPauseButton() {
        binding.pauseButton.apply {
            setOnClickListener {
                viewModel.onClickPauseResume()
                binding.stopButton.visibility = View.VISIBLE
                setImageResource(if (viewModel.isStarted) R.drawable.ic_pause else R.drawable.ic_play)
            }
        }
    }

    private fun initStopButton() {
        with(binding.stopButton) {
            setOnClickListener {
                viewModel.onClickStop()
            }
            visibility = View.GONE
        }
    }

    private fun bindViewModel() {
        with(viewModel) {
            collectFlow(timeCountState) {
                binding.currentMetricValue.text = it.toString()
            }
            collectFlow(closeScreenCommand) {
                if (!it.isNullOrBlank()) {
                    setFragmentResult(
                        FRAGMENT_RESULT_KEY,
                        bundleOf(TIME_TRACKING_KEY to it),
                    )
                    Navigator.closeFragment(parentFragmentManager)
                }
            }
        }
    }

    companion object {
        const val FRAGMENT_RESULT_KEY = "TrackerFragmentResult"
        const val TIME_TRACKING_KEY = "TIME_TRACKING_KEY"
    }
}
