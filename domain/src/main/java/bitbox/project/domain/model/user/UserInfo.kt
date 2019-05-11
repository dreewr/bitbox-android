package bitbox.project.domain.model.user

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfo @Inject constructor() {
//    "erro": 0,
//    "mensagem": "OK",
//    "saldo": 66.17,
//    "transacoesPendentes": [],
//    "idUsuario": 20,
//    "saldoPendente": 0

    var userBalance : Double? = 0.0
    var userID : Int? = 0
    var userName: String? = ""
}