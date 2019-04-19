package io.ubivis.tmppres.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.ubivis.domain.model.temporary.UserAuth
import io.ubivis.tmppres.TestApplication
import io.ubivis.tmppres.injection.module.ApplicationModule
import io.ubivis.tmppres.injection.module.DataModule
import io.ubivis.tmppres.injection.module.PresentationModule
import io.ubivis.tmppres.injection.module.RemoteModule
import javax.inject.Singleton

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 4/1/19.
 *
 * Approach do Joe Birch: usando AndroidInjection
 */

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
        ApplicationModule::class,
        PresentationModule::class,
        DataModule::class,
        RemoteModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    //Componente é criado pela Application
    fun inject(app: TestApplication)
}
