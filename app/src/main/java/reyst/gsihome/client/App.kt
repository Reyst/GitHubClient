package reyst.gsihome.client

import android.app.Application
import org.koin.android.ext.android.startKoin
import reyst.gsihome.client.di.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modulesForLoading)
    }


}