package io.ubivis.tmppres.injection.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 4/1/19.
 */

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: Application): Context

}