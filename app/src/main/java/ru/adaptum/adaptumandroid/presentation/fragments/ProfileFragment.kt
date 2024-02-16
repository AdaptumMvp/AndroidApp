package ru.adaptum.adaptumandroid.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.adaptum.adaptumandroid.AdaptumApp
import ru.adaptum.adaptumandroid.R
import ru.adaptum.adaptumandroid.databinding.FragmentProfileBinding
import ru.adaptum.adaptumandroid.presentation.common.Navigator
import ru.adaptum.adaptumandroid.presentation.common.collectFlow
import ru.adaptum.adaptumandroid.presentation.model.ProfileDataUI
import ru.adaptum.adaptumandroid.presentation.viewModels.ProfileFragmentViewModel
import javax.inject.Inject

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var viewModel: ProfileFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as AdaptumApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        viewModel.init()
    }

    private fun bindViewModel() {
        collectFlow(viewModel.profileDataState) {
            it?.let { it1 -> setViews(it1) }
        }
    }

    private fun setViews(profileDataUI: ProfileDataUI) {
        with(binding) {
            with(profileDataUI) {
                if (avatarUrl.isNotBlank()) {
                    Glide.with(requireContext()).load(avatarUrl)
                        .into(avatar)
                }
                nameTv.text = name
                with(profileAdditionalInfoJobLayout) {
                    title.text = getString(R.string.job)
                    description.text = profileDataUI.job
                    icon.setImageResource(R.drawable.ic_work)
                }
                with(profileAdditionalInfoOrganizationLayout) {
                    title.text = getString(R.string.organization)
                    description.text = profileDataUI.organization
                    icon.setImageResource(R.drawable.ic_business)
                }
                with(profileAdditionalInfoMailLayout) {
                    title.text = getString(R.string.mail)
                    description.text = profileDataUI.mail
                    icon.setImageResource(R.drawable.ic_mail)
                }
                with(profileAdditionalInfoCityLayout) {
                    title.text = getString(R.string.city)
                    description.text = profileDataUI.city
                    icon.setImageResource(R.drawable.ic_city)
                }
                logoutBtn.setOnClickListener {
                    viewModel.logout()
                    Navigator.navigateReplace(LoginFragment(), parentFragmentManager)
                }
            }
        }
    }
}
