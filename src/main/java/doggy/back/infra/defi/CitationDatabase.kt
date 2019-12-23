package doggy.back.infra.defi

import doggy.back.infra.doggies.DoggyDatabase
import javax.persistence.*

@Entity
@Table(name = "citations")
class CitationDatabase {
    @Id
    var id: Int = 0
    lateinit var texte: String
    @OneToMany
    @JoinTable(
        name = "auteurs_citations",
        joinColumns = [JoinColumn(
            name = "id_Citation"
        )],
        inverseJoinColumns = [JoinColumn(
            name = "trigramme"
        )]
    )
    lateinit var auteurs: List<DoggyDatabase>
}
