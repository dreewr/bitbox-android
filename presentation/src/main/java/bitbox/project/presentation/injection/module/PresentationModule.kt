package io.ubivis.tmppres.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.ubivis.domain.executor.PostExecutionThread
import io.ubivis.tmppres.UiThread
import io.ubivis.tmppres.MainActivity
import io.ubivis.tmppres.viewmodel.MainViewModel
import io.ubivis.tmppres.viewmodel.ViewModelFactory
import kotlin.reflect.KClass

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 4/1/19.
 *
 * Fornece
 */

@Module
abstract class PresentationModule{

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    //Injecting the Activity using dagger-android
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    //「View Model」

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: MainViewModel): ViewModel

}

//Definindo a anotação do ViewModel
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)