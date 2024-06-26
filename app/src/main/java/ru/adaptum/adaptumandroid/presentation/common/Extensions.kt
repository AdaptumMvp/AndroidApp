package ru.adaptum.adaptumandroid.presentation.common

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> AppCompatActivity.collectFlow(
    flow: Flow<T>,
    action: (T) -> Unit,
) {
    lifecycleScope.launch {
        flow.flowWithLifecycle(
            lifecycle,
            Lifecycle.State.CREATED,
        ).collectLatest {
            action(it)
        }
    }
}

fun <T> Fragment.collectFlow(
    flow: Flow<T>,
    action: (T) -> Unit,
) {
    lifecycleScope.launch {
        flow.flowWithLifecycle(
            lifecycle,
            Lifecycle.State.RESUMED,
        ).collectLatest {
            action(it)
        }
    }
}

fun Fragment.showToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

inline fun <reified T> fromJson(json: String): T {
    return Gson().fromJson(json, T::class.java)
}

inline fun <reified T> toJson(t: T): String {
    return Gson().toJson(t, T::class.java)
}
