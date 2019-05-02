package bitbox.project.domain.model.transaction

data class TransactionResponse(
    val erro: Int,
    val mensagem: String,
    val idTransacao: Int
)