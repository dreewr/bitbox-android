package bitbox.projec.remote.service

import bitbox.project.remote.service.OkHttpInterceptor
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/11/19.
 *
 * TODO: No método makeRetrofitService adicionar como dependência injetável o objeto UserAuth, de forma similar a como é feita a injeção de um contexto na criação de um objeto Database no módulo Cache, a transformando numa classe abstrata
 *
 */

object RetrofitServiceFactory {

    val BASE_URL: String = "https://dogechain.info/api/v1/"
    val AUTHORIZED_URL: String = ""


    //TODO: Descobrir como
    //https://stackoverflow.com/questions/18478258/android-retrofit-parameterized-headers
    fun makeRetrofitService(isDebug: Boolean): RetrofitService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor((isDebug)))
        return makeRetrofitService(okHttpClient, Gson())
    }

    fun makeAuthorizedRetrofitService(authToken: String): RetrofitService {
        val okHttpClient = makeOkHttpClient(
                makeAuthorizedLoggingInterceptor(authToken))
        return makeRetrofitService(okHttpClient, Gson())
    }

    //TODO: Avaliar o uso de URL Base dinâmica usando esse método
    private fun makeRetrofitService(okHttpClient: OkHttpClient, gson: Gson): RetrofitService {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(RetrofitService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(makeLoggingInterceptor(true))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {

            HttpLoggingInterceptor.Level.BODY

        } else {

            HttpLoggingInterceptor.Level.NONE

        }

        return logging
    }

    private fun makeAuthorizedLoggingInterceptor(authToken : String): OkHttpInterceptor  {
        val logging = OkHttpInterceptor(authToken)
        return logging
    }


}

