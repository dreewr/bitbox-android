package bitbox.project.presentation.injection.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bitbox.project.domain.executor.PostExecutionThread
import bitbox.project.presentation.UiThread
import bitbox.project.presentation.view.*
import bitbox.project.presentation.viewmodel.*
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


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

    @ContributesAndroidInjector
    abstract fun contributesLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributesProcessingActivity(): ProcessingActivity

    @ContributesAndroidInjector
    abstract fun contributesProductsActivity(): ProductsActivity

    @ContributesAndroidInjector
    abstract fun contributesVerificationActivity(): VerificationActivity

    //「View Model」

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    abstract fun bindProductsViewModel(viewModel: ProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProcessingViewModel::class)
    abstract fun bindProcessingViewModel(viewModel: ProcessingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VerificationViewModel::class)
    abstract fun bindVerificationViewModel(viewModel: ProductsViewModel): ViewModel

}

//Definindo a anotação do ViewModel
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)