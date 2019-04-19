package bitbox.project.presentation.injection

import android.app.Application
import bitbox.project.presentation.BitboxApplication
import bitbox.project.presentation.injection.module.ApplicationModule
import bitbox.project.presentation.injection.module.DataModule
import bitbox.project.presentation.injection.module.PresentationModule
import bitbox.project.presentation.injection.module.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

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
    fun inject(app: BitboxApplication)
}
