package bitbox.project.domain.model.transaction

data class Transaction(

    val estado: Int,
    val id: Int,
    val usuarioID: Int,
    val maquinaID: Int,
    val produtoID: Int,

    val erro: Int? = 0,
    val mensagem: String? = ""
)