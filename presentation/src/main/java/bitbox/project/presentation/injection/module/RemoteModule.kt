package bitbox.project.presentation.injection.module

import bitbox.projec.remote.RemoteImpl
import bitbox.projec.remote.service.RetrofitService
import bitbox.projec.remote.service.RetrofitServiceFactory
import bitbox.project.data.repository.Remote
import dagger.Binds
import dagger.Module
import dagger.Provides


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 4/1/19.
 */
@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        //Coloco aqui
//        fun provideRetrofitService(userAuth: UserAuth): RetrofitService {
//            return RetrofitServiceFactory.makeAuthorizedRetrofitService(userAuth.token)
//        }

        fun provideRetrofitService(): RetrofitService {
            return RetrofitServiceFactory.makeRetrofitService(isDebug = false)
        }


    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: RemoteImpl): Remote
}