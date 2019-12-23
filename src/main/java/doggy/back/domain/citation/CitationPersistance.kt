package doggy.back.domain.citation

interface CitationPersistance {
    fun getRandomCitation(): Citation
}