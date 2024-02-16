package ru.adaptum.adaptumandroid.di.modules

import dagger.Binds
import dagger.Module
import ru.adaptum.adaptumandroid.presentation.common.tracker.TimeTracker
import ru.adaptum.adaptumandroid.presentation.common.tracker.TimeTrackerImpl

@Module
abstract class HandlersModule {
    @Binds
    abstract fun provideTimerTracker(timerTracker: TimeTrackerImpl): TimeTracker

//    @Binds
//    abstract fun provideTokenDataHandler(tokenDataHandler: TokenDataHandlerImpl): TokenDataHandler
}
