package ru.adaptum.adaptumandroid.di.modules

import dagger.Binds
import dagger.Module
import ru.adaptum.adaptumandroid.data.repository.AuthRepositoryImpl
import ru.adaptum.adaptumandroid.data.repository.EventsRepositoryImpl
import ru.adaptum.adaptumandroid.data.repository.MessagesRepositoryImpl
import ru.adaptum.adaptumandroid.data.repository.PlansRepositoryImpl
import ru.adaptum.adaptumandroid.data.repository.ProfileDataRepositoryImpl
import ru.adaptum.adaptumandroid.domain.repository.AuthRepository
import ru.adaptum.adaptumandroid.domain.repository.EventsRepository
import ru.adaptum.adaptumandroid.domain.repository.MessagesRepository
import ru.adaptum.adaptumandroid.domain.repository.PlansRepository
import ru.adaptum.adaptumandroid.domain.repository.ProfileDataRepository

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun provideProfileDataRepository(repository: ProfileDataRepositoryImpl): ProfileDataRepository

    @Binds
    abstract fun provideEventsRepository(repository: EventsRepositoryImpl): EventsRepository

    @Binds
    abstract fun provideMessagesRepository(repository: MessagesRepositoryImpl): MessagesRepository

    @Binds
    abstract fun provideAdaptListRepository(adaptListRepositoryImpl: PlansRepositoryImpl): PlansRepository
}
