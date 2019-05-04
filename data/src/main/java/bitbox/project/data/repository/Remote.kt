package bitbox.project.data.repository

import bitbox.project.domain.model.machine.BitboxItems
import bitbox.project.domain.model.user.User
import io.reactivex.Observable

import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/8/19.
 */

interface Remote {

    fun createTransaction(newTransaction : Transaction): Observable<TransactionResponse>
    fun getBitboxItems(bitboxID: String): Observable<BitboxItems>
    fun executeUserLogin(username: String, password: String): Observable<User>

}