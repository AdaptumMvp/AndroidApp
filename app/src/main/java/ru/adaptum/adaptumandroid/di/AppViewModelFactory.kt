package ru.adaptum.adaptumandroid.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class AppViewModelFactory
    @Inject
    constructor(
        private val viewModelProviders: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>,
    ) :
    ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(viewModelProviders[modelClass]) {
                if (this != null) this.get() as T else throw IllegalArgumentException("Incorrect ViewModel")
            }
    }
