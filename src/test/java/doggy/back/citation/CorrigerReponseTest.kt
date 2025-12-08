package doggy.back.citation

import doggy.back.domain.citation.CalculateurScore
import doggy.back.domain.citation.CitationNonTrouveException
import doggy.back.domain.citation.CorrigerReponse
import doggy.back.domain.citation.PartieTermineeException
import doggy.back.domain.partie.Partie
import doggy.back.domain.partie.PartiePersistance
import doggy.back.domain.partie.PartieStatut
import doggy.back.infra.parties.PartieNonTrouveeException
import doggy.back.rest.quizz.PartieController
import doggy.back.domain.citation.CitationPersistance
import doggy.back.domain.citation.Citation
import doggy.back.domain.doggy.Doggy
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
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
    fun `reponse correcte rapide doit ajouter 100 points`() {
        val partie = Partie("partie-1", "joueur1", 0, PartieStatut.EN_COURS)
        val auteur = Doggy("ABC", "Nom", "Prenom", "Surnom", "photo.jpg", "Tribu", "Signe", "email@test.fr")
        val citation = Citation(1, "citation test", listOf(auteur))
        val reponse = PartieController.Reponse(1, "ABC", 3000L)

        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat = corrigerReponse.execute("partie-1", reponse)

        assertThat(resultat.correct).isTrue()
        assertThat(resultat.pointsGagnes).isEqualTo(100)
        verify(partiePersistance).sauverPartie(partie.copy(score = 100))
    }

    @Test
    fun `reponse correcte moyenne doit ajouter 75 points`() {
        val partie = Partie("partie-1", "joueur1", 50, PartieStatut.EN_COURS)
        val auteur = Doggy("ABC", "Nom", "Prenom", "Surnom", "photo.jpg", "Tribu", "Signe", "email@test.fr")
        val citation = Citation(1, "citation test", listOf(auteur))
        val reponse = PartieController.Reponse(1, "ABC", 7000L)

        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat = corrigerReponse.execute("partie-1", reponse)

        assertThat(resultat.correct).isTrue()
        assertThat(resultat.pointsGagnes).isEqualTo(75)
        verify(partiePersistance).sauverPartie(partie.copy(score = 125))
    }

    @Test
    fun `reponse correcte lente doit ajouter 50 points`() {
        val partie = Partie("partie-1", "joueur1", 100, PartieStatut.EN_COURS)
        val auteur = Doggy("ABC", "Nom", "Prenom", "Surnom", "photo.jpg", "Tribu", "Signe", "email@test.fr")
        val citation = Citation(1, "citation test", listOf(auteur))
        val reponse = PartieController.Reponse(1, "ABC", 12000L)

        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat = corrigerReponse.execute("partie-1", reponse)

        assertThat(resultat.correct).isTrue()
        assertThat(resultat.pointsGagnes).isEqualTo(50)
        verify(partiePersistance).sauverPartie(partie.copy(score = 150))
    }

    @Test
    fun `reponse incorrecte doit retirer 10 points`() {
        val partie = Partie("partie-1", "joueur1", 100, PartieStatut.EN_COURS)
        val auteur = Doggy("ABC", "Nom", "Prenom", "Surnom", "photo.jpg", "Tribu", "Signe", "email@test.fr")
        val citation = Citation(1, "citation test", listOf(auteur))
        val reponse = PartieController.Reponse(1, "XYZ", 3000L)

        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat = corrigerReponse.execute("partie-1", reponse)

        assertThat(resultat.correct).isFalse()
        assertThat(resultat.pointsGagnes).isEqualTo(-10)
        verify(partiePersistance).sauverPartie(partie.copy(score = 90))
    }

    @Test
    fun `reponse sans temps doit utiliser 0ms et donner 100 points si correcte`() {
        val partie = Partie("partie-1", "joueur1", 0, PartieStatut.EN_COURS)
        val auteur = Doggy("ABC", "Nom", "Prenom", "Surnom", "photo.jpg", "Tribu", "Signe", "email@test.fr")
        val citation = Citation(1, "citation test", listOf(auteur))
        val reponse = PartieController.Reponse(1, "ABC", null)

        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat = corrigerReponse.execute("partie-1", reponse)

        assertThat(resultat.correct).isTrue()
        assertThat(resultat.pointsGagnes).isEqualTo(100)
        verify(partiePersistance).sauverPartie(partie.copy(score = 100))
    }

    @Test
    fun `score doit s'accumuler sur plusieurs reponses`() {
        val partie1 = Partie("partie-1", "joueur1", 0, PartieStatut.EN_COURS)
        val partie2 = Partie("partie-1", "joueur1", 100, PartieStatut.EN_COURS)
        val auteur = Doggy("ABC", "Nom", "Prenom", "Surnom", "photo.jpg", "Tribu", "Signe", "email@test.fr")
        val citation = Citation(1, "citation test", listOf(auteur))
        val reponse = PartieController.Reponse(1, "ABC", 3000L)

        `when`(partiePersistance.recupererPartie("partie-1"))
            .thenReturn(partie1)
            .thenReturn(partie2)
        `when`(citationPersistance.recupererCitation(1)).thenReturn(citation)

        val resultat1 = corrigerReponse.execute("partie-1", reponse)
        assertThat(resultat1.pointsGagnes).isEqualTo(100)

        val resultat2 = corrigerReponse.execute("partie-1", reponse)
        assertThat(resultat2.pointsGagnes).isEqualTo(100)

        verify(partiePersistance).sauverPartie(partie1.copy(score = 100))
        verify(partiePersistance).sauverPartie(partie2.copy(score = 200))
    }

    @Test(expected = PartieNonTrouveeException::class)
    fun `doit lever exception si partie non trouvee`() {
        val reponse = PartieController.Reponse(1, "ABC", 3000L)
        `when`(partiePersistance.recupererPartie("partie-inexistante")).thenReturn(null)

        corrigerReponse.execute("partie-inexistante", reponse)
    }

    @Test(expected = PartieTermineeException::class)
    fun `doit lever exception si partie terminee`() {
        val partie = Partie("partie-1", "joueur1", 100, PartieStatut.TERMINEE)
        val reponse = PartieController.Reponse(1, "ABC", 3000L)
        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)

        corrigerReponse.execute("partie-1", reponse)
    }

    @Test(expected = CitationNonTrouveException::class)
    fun `doit lever exception si citation non trouvee`() {
        val partie = Partie("partie-1", "joueur1", 0, PartieStatut.EN_COURS)
        val reponse = PartieController.Reponse(999, "ABC", 3000L)
        `when`(partiePersistance.recupererPartie("partie-1")).thenReturn(partie)
        `when`(citationPersistance.recupererCitation(999)).thenReturn(null)

        corrigerReponse.execute("partie-1", reponse)
    }
}
