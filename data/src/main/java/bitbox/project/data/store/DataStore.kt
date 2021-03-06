package bitbox.project.data.store

import bitbox.project.domain.model.machine.BitboxItems
import bitbox.project.domain.model.user.User
import io.reactivex.Observable

import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import bitbox.project.domain.model.user.Pin
import bitbox.project.domain.model.user.PinResponse

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/8/19.
 *
 * Interface simples que serve como contrato entre todos os DataStore, para que exista uma consistência de métodos que DataRepository possa acessar.
 */

interface DataStore{

    fun getTransaction(transactionID: String): Observable<Transaction>
    fun createTransaction(newTransaction : Transaction): Observable<TransactionResponse>
    fun executeUserLogin(username: String, password: String): Observable<User>
    fun getBitboxItems(bitboxID: String): Observable<BitboxItems>
    fun checkPin(userID: String, pin: Pin): Observable<PinResponse>
}