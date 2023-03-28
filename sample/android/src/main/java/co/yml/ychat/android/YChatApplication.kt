package co.yml.ychat.android

import android.app.Application
import co.yml.ychat.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class YChatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@YChatApplication)
            modules(appModule)
        }
    }
}
