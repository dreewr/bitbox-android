package bitbox.project.domain.model.user

data class User(
    val erro: Int? = 0,
    val idUsuario: Int,
    val mensagem: String = "",
    val saldo: Double,
    val saldoPendente: Int,
    val transacoesPendentes: List<Any>
)