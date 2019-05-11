package bitbox.project.domain.model.transaction

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionInfo @Inject constructor(){

    var machineID : Int = 0
    var transactionID: Int = 0
    var productID: Int = 0

}