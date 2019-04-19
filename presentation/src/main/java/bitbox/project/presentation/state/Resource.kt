package io.ubivis.tmppres.state

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/30/19.
 *
 * Classe genérica que encapsula dados passados. Serve para fornecer informações sobre o status do objeto assim como os dados.
 */

class Resource<out T> constructor(val status: ResourceState,
                                  val data: T?,
                                  val message: String?) {

    fun <T> success(data: T): Resource<T> {
        return Resource(ResourceState.SUCCESS, data, null)
    }

    fun <T> error(message: String?): Resource<T> {
        return Resource(ResourceState.ERROR, null, message)
    }

    fun <T> loading(): Resource<T> {
        return Resource(ResourceState.LOADING, null, null)
    }

}