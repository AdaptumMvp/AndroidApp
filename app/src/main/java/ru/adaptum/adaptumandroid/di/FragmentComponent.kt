package ru.adaptum.adaptumandroid.di

import dagger.BindsInstance
import dagger.Subcomponent
import ru.adaptum.adaptumandroid.presentation.fragments.StageFragment
import ru.adaptum.adaptumandroid.presentation.model.StageListItem

@Subcomponent
interface FragmentComponent {
    fun inject(stageFragment: StageFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance stageListItem: StageListItem,
        ): FragmentComponent
    }
}
