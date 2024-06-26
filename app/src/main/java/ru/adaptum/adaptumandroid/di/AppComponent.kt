package ru.adaptum.adaptumandroid.di

import dagger.Component
import ru.adaptum.adaptumandroid.di.modules.AppModule
import ru.adaptum.adaptumandroid.di.modules.HandlersModule
import ru.adaptum.adaptumandroid.di.modules.NetworkModule
import ru.adaptum.adaptumandroid.di.modules.RepositoryModule
import ru.adaptum.adaptumandroid.di.modules.ViewModelModule
import ru.adaptum.adaptumandroid.presentation.MainActivity
import ru.adaptum.adaptumandroid.presentation.fragments.AdaptPlansFragment
import ru.adaptum.adaptumandroid.presentation.fragments.ChatFragment
import ru.adaptum.adaptumandroid.presentation.fragments.EventsFragment
import ru.adaptum.adaptumandroid.presentation.fragments.LoginFragment
import ru.adaptum.adaptumandroid.presentation.fragments.ProfileFragment
import ru.adaptum.adaptumandroid.presentation.fragments.StagesFragment
import ru.adaptum.adaptumandroid.presentation.fragments.TrackerFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        HandlersModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
    ],
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(profileFragment: ProfileFragment)

    fun inject(eventsFragment: EventsFragment)

    fun inject(stagesFragment: StagesFragment)

    fun inject(trackerFragment: TrackerFragment)

    fun inject(loginFragment: LoginFragment)

    fun inject(adaptPlansFragment: AdaptPlansFragment)

    fun inject(stageFragment: ChatFragment)

    fun fragmentComponentFactory(): FragmentComponent.Factory
}
