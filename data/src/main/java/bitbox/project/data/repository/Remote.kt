package bitbox.project.data.repository

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
 */

interface Remote {

    fun getTransaction(transactionID : String): Observable<Transaction>
    fun createTransaction(newTransaction : Transaction): Observable<TransactionResponse>
    fun getBitboxItems(bitboxID: String): Observable<BitboxItems>
    fun executeUserLogin(username: String, password: String): Observable<User>
    fun checkPin(userID: String, pin: Pin): Observable<PinResponse>

}