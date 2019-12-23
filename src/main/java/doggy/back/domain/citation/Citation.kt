package doggy.back.domain.citation

import doggy.back.domain.doggy.Doggy

data class Citation(
    val id: Int,
    val texte: String,
    val auteurs: List<Doggy>
)