package doggy.back.rest.quizz

data class PartieApi(
    val id: String,
    val joueur: String,
    val score: Int,
    val statut: String
)