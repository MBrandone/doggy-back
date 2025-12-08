package doggy.back.domain.citation

import doggy.back.domain.doggy.Doggy
import doggy.back.domain.partie.CalculateurScore
import doggy.back.domain.partie.Partie
import doggy.back.domain.partie.PartiePersistance
import doggy.back.domain.partie.PartieStatut
import doggy.back.infra.parties.PartieNonTrouveeException
import doggy.back.rest.quizz.PartieController
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CorrigerReponseTest {

    @Mock
    private lateinit var partiePersistance: PartiePersistance

    @Mock
    private lateinit var citationPersistance: CitationPersistance

    @InjectMocks
    private lateinit var calculateurScore: CalculateurScore

    @InjectMocks
    private lateinit var corrigerReponse: CorrigerReponse

    @Test
    fun `devrait calculer et sauvegarder le score pour une reponse rapide correcte`() {
        val partie = Partie("partie-1", "joueur-1", 0, PartieStatut.EN_COURS)
        val citation = Citation(
            id = 1,
            texte = "Test citation",
            auteurs = listOf(Doggy(trigramme = "AAA", nom = "Test", prenom = "Auteur", surnom = "AT", photo = "", tribu = "", signeParticulier = "", email = ""))
        )
        val reponse = PartieController.Reponse(1, "AAA", 3000L)

        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat = corrigerReponse.execute("partie-1", reponse)

        assertThat(resultat.correct).isTrue()
        assertThat(resultat.pointsGagnes).isEqualTo(100)
        verify(partiePersistance).sauvegarder(argThat { it.score == 100 })
    }

    @Test
    fun `devrait calculer et sauvegarder le score pour une reponse moyenne correcte`() {
        val partie = Partie("partie-1", "joueur-1", 0, PartieStatut.EN_COURS)
        val citation = Citation(
            id = 1,
            texte = "Test citation",
            auteurs = listOf(Doggy(trigramme = "BBB", nom = "Test", prenom = "Auteur", surnom = "BT", photo = "", tribu = "", signeParticulier = "", email = ""))
        )
        val reponse = PartieController.Reponse(1, "BBB", 7000L)

        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat = corrigerReponse.execute("partie-1", reponse)

        assertThat(resultat.correct).isTrue()
        assertThat(resultat.pointsGagnes).isEqualTo(75)
        verify(partiePersistance).sauvegarder(argThat { it.score == 75 })
    }

    @Test
    fun `devrait calculer et sauvegarder le score pour une reponse lente correcte`() {
        val partie = Partie("partie-1", "joueur-1", 0, PartieStatut.EN_COURS)
        val citation = Citation(
            id = 1,
            texte = "Test citation",
            auteurs = listOf(Doggy(trigramme = "CCC", nom = "Test", prenom = "Auteur", surnom = "CT", photo = "", tribu = "", signeParticulier = "", email = ""))
        )
        val reponse = PartieController.Reponse(1, "CCC", 12000L)

        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat = corrigerReponse.execute("partie-1", reponse)

        assertThat(resultat.correct).isTrue()
        assertThat(resultat.pointsGagnes).isEqualTo(50)
        verify(partiePersistance).sauvegarder(argThat { it.score == 50 })
    }

    @Test
    fun `devrait retirer des points pour une mauvaise reponse`() {
        val partie = Partie("partie-1", "joueur-1", 100, PartieStatut.EN_COURS)
        val citation = Citation(
            id = 1,
            texte = "Test citation",
            auteurs = listOf(Doggy(trigramme = "AAA", nom = "Test", prenom = "Auteur", surnom = "AT", photo = "", tribu = "", signeParticulier = "", email = ""))
        )
        val reponse = PartieController.Reponse(1, "WRONG", 3000L)

        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat = corrigerReponse.execute("partie-1", reponse)

        assertThat(resultat.correct).isFalse()
        assertThat(resultat.pointsGagnes).isEqualTo(-10)
        verify(partiePersistance).sauvegarder(argThat { it.score == 90 })
    }

    @Test
    fun `devrait accumuler le score sur plusieurs reponses`() {
        val partie1 = Partie("partie-1", "joueur-1", 0, PartieStatut.EN_COURS)
        val partie2 = Partie("partie-1", "joueur-1", 100, PartieStatut.EN_COURS)
        val citation = Citation(
            id = 1,
            texte = "Test citation",
            auteurs = listOf(Doggy(trigramme = "AAA", nom = "Test", prenom = "Auteur", surnom = "AT", photo = "", tribu = "", signeParticulier = "", email = ""))
        )
        val reponse1 = PartieController.Reponse(1, "AAA", 3000L)
        val reponse2 = PartieController.Reponse(1, "AAA", 7000L)

        `when`(partiePersistance.recupererPartie("partie-1"))
            .thenReturn(partie1)
            .thenReturn(partie2)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat1 = corrigerReponse.execute("partie-1", reponse1)
        val resultat2 = corrigerReponse.execute("partie-1", reponse2)

        assertThat(resultat1.pointsGagnes).isEqualTo(100)
        assertThat(resultat2.pointsGagnes).isEqualTo(75)
        verify(partiePersistance).sauvegarder(argThat { it.score == 100 })
        verify(partiePersistance).sauvegarder(argThat { it.score == 175 })
    }

    @Test
    fun `devrait utiliser 0ms si tempsReponse est null (retrocompatibilite)`() {
        val partie = Partie("partie-1", "joueur-1", 0, PartieStatut.EN_COURS)
        val citation = Citation(
            id = 1,
            texte = "Test citation",
            auteurs = listOf(Doggy(trigramme = "AAA", nom = "Test", prenom = "Auteur", surnom = "AT", photo = "", tribu = "", signeParticulier = "", email = ""))
        )
        val reponse = PartieController.Reponse(1, "AAA", null)

        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat = corrigerReponse.execute("partie-1", reponse)

        assertThat(resultat.correct).isTrue()
        assertThat(resultat.pointsGagnes).isEqualTo(100)
    }

    @Test(expected = PartieNonTrouveeException::class)
    fun `devrait lever une exception si la partie n'existe pas`() {
        `when`(partiePersistance.recupererPartie("inexistant")).thenReturn(null)
        val reponse = PartieController.Reponse(1, "AAA", 3000L)
        corrigerReponse.execute("inexistant", reponse)
    }

    @Test(expected = PartieTermineeException::class)
    fun `devrait lever une exception si la partie est terminee`() {
        val partie = Partie("partie-1", "joueur-1", 100, PartieStatut.TERMINEE)
        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        val reponse = PartieController.Reponse(1, "AAA", 3000L)
        corrigerReponse.execute("partie-1", reponse)
    }

    @Test(expected = CitationNonTrouveException::class)
    fun `devrait lever une exception si la citation n'existe pas`() {
        val partie = Partie("partie-1", "joueur-1", 0, PartieStatut.EN_COURS)
        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(999)).thenReturn(null)
        val reponse = PartieController.Reponse(999, "AAA", 3000L)
        corrigerReponse.execute("partie-1", reponse)
    }
}
