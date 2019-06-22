package bitbox.project.remote.service

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 4/2/19.
 *
 * Classe de testes usada para criar headers nas chamadas programaticamente. Uma das alternativas sendo testadas
 *
 * Injeção da String no construtor sendo injetada normalmente
 */


class OkHttpInterceptor constructor (private val authToken: String) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest: Request = chain.request()

        val newRequest: Request = originalRequest.newBuilder()
                .header("X-AUTH-TOKEN", authToken)
                .build();

        if(originalRequest.url().toString().contains("transacoes")){
            originalRequest.body()
        }
        return chain.proceed(newRequest)
    }
}