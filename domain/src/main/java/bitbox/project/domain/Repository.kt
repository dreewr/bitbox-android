package bitbox.project.domain

import bitbox.project.domain.model.machine.BitboxItems
import bitbox.project.domain.model.user.User
import bitbox.project.domain.model.transaction.Transaction
import bitbox.project.domain.model.transaction.TransactionResponse
import io.reactivex.Observable


/**
 * Created by André Luiz Rodrigues dos Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/1/19.
 *
 * Classe que centraliza todas as operações de dados dos aplicativos. Cada função
 * definida aqui está ligada a uma classe de use-case
 */

interface Repository{

    fun executeUserLogin(username: String, password: String): Observable<User>

    fun getBitboxItems(bitboxID: String): Observable<BitboxItems>

    fun createTransaction(newTransaction : Transaction): Observable<TransactionResponse>

    fun getTransaction(transactionID: String): Observable<Transaction>

}
