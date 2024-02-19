package ru.adaptum.adaptumandroid.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ru.adaptum.adaptumandroid.AdaptumApp
import ru.adaptum.adaptumandroid.databinding.FragmentLoginBinding
import ru.adaptum.adaptumandroid.presentation.common.Navigator
import ru.adaptum.adaptumandroid.presentation.common.ToolbarVisibilityListener
import ru.adaptum.adaptumandroid.presentation.common.collectFlow
import ru.adaptum.adaptumandroid.presentation.common.showToast
import ru.adaptum.adaptumandroid.presentation.viewModels.LoginFragmentViewModel
import javax.inject.Inject

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModel: LoginFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as AdaptumApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        (requireActivity() as? ToolbarVisibilityListener)?.hideToolbar()
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViewModel()
    }

    private fun initViews() {
        binding.submitButton.setOnClickListener {
            viewModel.onClickAuth(
                binding.loginTv.text.toString(),
                binding.passwordTv.text.toString(),
            )
        }
    }

    private fun bindViewModel() {
        collectFlow(viewModel.loginFragmentState) {
            if (it.success) {
                binding.progressBar.isVisible = false
                openPlansScreen()
                return@collectFlow
            }
            if (it.loading) {
                binding.progressBar.isVisible = true
            }
            if (!it.error.isNullOrBlank()) {
                binding.progressBar.isVisible = false
                showToast(it.error)
            }
        }
    }

    private fun openPlansScreen() {
        Navigator.navigateReplace(AdaptPlansFragment(), parentFragmentManager)
        (requireActivity() as? ToolbarVisibilityListener)?.showToolbar()
    }
}
