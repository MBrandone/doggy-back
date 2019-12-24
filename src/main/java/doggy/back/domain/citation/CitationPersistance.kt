package doggy.back.domain.citation

interface CitationPersistance {
    fun getRandomCitation(): Citation
    fun recupererCitation(idCitation: Int): Citation?
}