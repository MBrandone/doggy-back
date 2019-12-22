package doggy.back.domain.entites

data class Citation(
    val id: String,
    val texte: String,
    val auteurs: List<Doggy>
)