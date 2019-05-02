package bitbox.project.domain.model.transaction

data class Transaction(
    val estado: Int,
    val id: Int,
    val maquinaID: Int,
    val produtoID: Int,
    val usuarioID: Int,
    val erro: Int? = 0,
    val mensagem: String? = ""
)