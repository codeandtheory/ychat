package co.yml.ychatgpt.android

import android.app.Application
import co.yml.ychatgpt.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@AppApplication)
            modules(appModule)
        }
    }
}