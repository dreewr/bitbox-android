package io.ubivis.tmppres

import android.app.Activity
import android.app.Application
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.ubivis.tmppres.injection.DaggerApplicationComponent
import javax.inject.Inject

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/27/19.
 */
class TestApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()

        Log.d("DEBUGANDO", "Application Created")
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

}

