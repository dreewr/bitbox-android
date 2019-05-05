package bitbox.project.domain.model.machine

data class Item(
    val maquinaId: Int,
    val produtoId: Int,
    val produtoNome: String,
    val produtoPreco: Double,
    val produtoImagem: String? = "https://static.carrefour.com.br/medias/sys_master/images/images/h21/he0/h00/h00/11096531959838.jpg")
