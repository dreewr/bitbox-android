package io.ubivis.tmppres.injection.module

import dagger.Binds
import dagger.Module
import io.ubivis.domain.Repository
import io.ubivis.data.DataRepository

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 4/1/19.
 *
 * Módulo Dagger de configuração das dependências do módulo Data.
 * Une a abstração do contrato Repository na classe Domain á implementação Data Repository no módulo Data.
 */

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: DataRepository): Repository
}
