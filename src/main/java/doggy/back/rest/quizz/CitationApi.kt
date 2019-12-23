package doggy.back.rest.quizz

data class CitationApi(
    val id: Int,
    val texte: String,
    val proposition: List<String>
)