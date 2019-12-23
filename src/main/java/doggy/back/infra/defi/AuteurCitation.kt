package doggy.back.infra.defi

import doggy.back.infra.doggies.DoggyDatabase
import javax.persistence.EmbeddedId
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId


class AuteurCitation {
    @EmbeddedId
    var id: AuteurCitationKey? = null
    @ManyToOne
    @MapsId("id_citation")
    @JoinColumn(name = "id_citation")
    var citationDatabase: CitationDatabase? = null
    @ManyToOne
    @MapsId("trigramme")
    @JoinColumn(name = "trigramme")
    var auteur: DoggyDatabase? = null
}