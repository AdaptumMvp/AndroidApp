@file:Suppress("ktlint:standard:function-naming")

package ru.adaptum.adaptumandroid.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.filterNotNull
import ru.adaptum.adaptumandroid.AdaptumApp
import ru.adaptum.adaptumandroid.R
import ru.adaptum.adaptumandroid.presentation.common.Navigator
import ru.adaptum.adaptumandroid.presentation.common.collectFlow
import ru.adaptum.adaptumandroid.presentation.model.ProfileDataUI
import ru.adaptum.adaptumandroid.presentation.viewModels.ProfileFragmentViewModel
import javax.inject.Inject

class ProfileFragment : Fragment() {
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
    ) = ComposeView(requireContext()).apply {
        setContent {
            ProfileScreen(viewModel)
        }
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
        collectFlow(viewModel.logoutCommand.filterNotNull()) {
            Navigator.navigateReplace(LoginFragment(), parentFragmentManager)
        }
    }
}

@Composable
fun ProfileScreen(viewModel: ProfileFragmentViewModel) {
    val profileData by viewModel.profileState
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.primary)),
    ) {
        Column {
            ProfileHeader(profileData, viewModel::logout)
            ProfileAdditionalInfo(profileData)
        }
    }
}

@Composable
fun ProfileHeader(
    profileData: ProfileDataUI?,
    logout: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.secondary))
                .padding(15.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = profileData?.name ?: "",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier.width(120.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                onClick = {
                    logout()
                },
                colors =
                    ButtonDefaults.outlinedButtonColors(
                        contentColor = colorResource(id = R.color.success),
                        containerColor =
                            colorResource(id = R.color.secondary),
                    ),
                border = BorderStroke(1.dp, colorResource(id = R.color.success)),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_logout),
                    contentDescription = null,
                    tint = colorResource(id = R.color.success),
                )
                Text(
                    text = stringResource(R.string.logout),
                    color = colorResource(id = R.color.success),
                )
            }
        }
    }
}

@Composable
fun ProfileAdditionalInfo(profileData: ProfileDataUI?) {
    Column(
        modifier =
            Modifier
                .background(color = colorResource(id = R.color.primary))
                .padding(top = 10.dp),
    ) {
        ProfileAdditionalInfoItem(
            icon = R.drawable.ic_work,
            title = stringResource(id = R.string.job),
            description = profileData?.job ?: "",
        )
        ProfileAdditionalInfoItem(
            icon = R.drawable.ic_business,
            title = stringResource(id = R.string.organization),
            description = profileData?.organization ?: "",
        )
        ProfileAdditionalInfoItem(
            icon = R.drawable.ic_mail,
            title = stringResource(id = R.string.mail),
            description = profileData?.mail ?: "",
        )
        ProfileAdditionalInfoItem(
            icon = R.drawable.ic_city,
            title = stringResource(id = R.string.city),
            description = profileData?.city ?: "",
        )
    }
}

@Composable
fun ProfileAdditionalInfoItem(
    icon: Int,
    title: String,
    description: String,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 5.dp, bottom = 5.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(35.dp),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.success)),
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                )
                Text(
                    text = description,
                    color = colorResource(id = R.color.text_secondary),
                )
                HorizontalDivider(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 5.dp),
                    thickness = 2.dp,
                    color = Color.Gray,
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(viewModel())
}
