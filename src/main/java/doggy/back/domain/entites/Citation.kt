package doggy.back.domain.entites

data class Citation(
    val id: Int,
    val texte: String,
    val auteurs: List<Doggy>
)