package bitbox.project.domain.model.machine

data class BitboxItems(
    val erro: Int,
    val idMaquina: Int,
    val itensDisponiveis: List<Item>,
    val mensagem: String
)
