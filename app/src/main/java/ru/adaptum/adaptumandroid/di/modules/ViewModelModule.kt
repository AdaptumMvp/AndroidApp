package ru.adaptum.adaptumandroid.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.adaptum.adaptumandroid.di.AppViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(ProfileFragmentViewModel::class)
//    internal abstract fun bindProfileFragmentViewModel(viewModel: ProfileFragmentViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(EventsFragmentViewModel::class)
//    internal abstract fun bindEventsFragmentViewModel(viewModel: EventsFragmentViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(StagesFragmentViewModel::class)
//    internal abstract fun bindTasksFragmentViewModel(viewModel: StagesFragmentViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginFragmentViewModel::class)
//    internal abstract fun bindLoginFragmentViewModel(viewModel: LoginFragmentViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainActivityViewModel::class)
//    internal abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(StageFragmentViewModel::class)
//    internal abstract fun bindStageFragmentViewModel(viewModel: StageFragmentViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ChatFragmentViewModel::class)
//    internal abstract fun bindChatFragmentViewModel(viewModel: ChatFragmentViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(AdaptPlansFragmentViewModel::class)
//    internal abstract fun bindAdaptPlansFragmentViewModel(viewModel: AdaptPlansFragmentViewModel): ViewModel
}
