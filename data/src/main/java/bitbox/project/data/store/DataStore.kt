package bitbox.project.data.store

import io.reactivex.Observable

import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/8/19.
 *
 * Interface simples que serve como contrato entre todos os DataStore, para que exista uma consistência de métodos que DataRepository possa acessar.
 */

interface DataStore{

    fun createTransaction(newTransaction : Transaction): Observable<TransactionResponse>

}