package bitbox.project.domain.model.user

//Body da requisição de login
data class UserAuth(
    val nome: String,
    val senha: String
)
