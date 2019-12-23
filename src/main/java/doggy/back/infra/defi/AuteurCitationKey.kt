package doggy.back.infra.defi

import java.io.Serializable
import javax.persistence.Column

import javax.persistence.Embeddable


@Embeddable
class AuteurCitationKey : Serializable {
    @Column(name = "id_citation")
    var idCitation: Long? = null
    @Column(name = "trigramme")
    var trigramme: Long? = null
}