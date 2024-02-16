package ru.adaptum.adaptumandroid.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.google.gson.Gson
import ru.adaptum.adaptumandroid.AdaptumApp
import ru.adaptum.adaptumandroid.databinding.FragmentStageBinding
import ru.adaptum.adaptumandroid.presentation.common.Navigator
import ru.adaptum.adaptumandroid.presentation.common.collectFlow
import ru.adaptum.adaptumandroid.presentation.model.StageListItem
import ru.adaptum.adaptumandroid.presentation.viewModels.StageFragmentViewModel
import javax.inject.Inject

class StageFragment : Fragment() {
    private lateinit var binding: FragmentStageBinding

    @Inject
    lateinit var viewModel: StageFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as AdaptumApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        arguments?.getString(STAGE_PARAM)?.let {
            viewModel.init(
                Gson().fromJson(
                    it,
                    StageListItem::class.java,
                ) as StageListItem,
            )
        }
        setFragmentResultListener(TrackerFragment.FRAGMENT_RESULT_KEY) { _, bundle ->
            binding.timeTrackingValue.text = bundle.getString(TrackerFragment.TIME_TRACKING_KEY)
        }
    }

    private fun bindViewModel() {
        collectFlow(viewModel.stageDataState) {
            it?.let { it1 -> setViews(it1) }
        }
    }

    private fun setViews(stageListItem: StageListItem) {
        with(binding) {
            titleTv.text = stageListItem.name
            descriptionTv.text = stageListItem.description
            if (stageListItem.documentUrl.isEmpty()) {
                url.isVisible = false
            } else {
                url.text = stageListItem.documentUrl
            }
            startTimerBtn.setOnClickListener {
                Navigator.navigateReplaceSaveStack(TrackerFragment(), parentFragmentManager)
            }
            acceptTaskBtn.setOnClickListener {
                viewModel.onClickAccept(1)
                Navigator.closeFragment(parentFragmentManager)
            }
            if (stageListItem.videoUrl.isEmpty()) {
                videoView.isVisible = false
                return
            }
            val videoStr =
                "<html><body>Promo video<br><iframe width=\"360\" height=\"240\" src=\"${stageListItem.videoUrl}\" frameborder=\"0\" allowfullscreen></iframe></body></html>"
            videoView.webViewClient =
                object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView,
                        url: String,
                    ): Boolean {
                        return false
                    }
                }
            videoView.settings.javaScriptEnabled = true
            videoView.loadData(videoStr, "text/html", "utf-8")
        }
    }

    companion object {
        private const val STAGE_PARAM = "STAGE"

        fun getInstance(stageListItem: StageListItem) =
            StageFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(STAGE_PARAM, Gson().toJson(stageListItem))
                    }
            }
    }
}
