package ru.adaptum.adaptumandroid

import android.app.Application
import ru.adaptum.adaptumandroid.di.DaggerAppComponent
import ru.adaptum.adaptumandroid.di.modules.AppModule

class AdaptumApp : Application() {
    val appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
}
