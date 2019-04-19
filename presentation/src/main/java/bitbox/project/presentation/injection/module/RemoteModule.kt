package io.ubivis.tmppres.injection.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ubivis.data.repository.Remote
import io.ubivis.domain.model.temporary.UserAuth
import io.ubivis.remote.RemoteImpl
import io.ubivis.remote.service.RetrofitService
import io.ubivis.remote.service.RetrofitServiceFactory
import io.ubivis.tmppres.BuildConfig

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
        fun provideRetrofitService(userAuth: UserAuth): RetrofitService {
            return RetrofitServiceFactory.makeAuthorizedRetrofitService(userAuth.token)
        }

    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: RemoteImpl): Remote
}