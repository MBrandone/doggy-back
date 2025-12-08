package doggy.back.citation

import doggy.back.domain.citation.CalculateurScore
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculateurScoreTest {

    @InjectMocks
    private lateinit var calculateurScore: CalculateurScore

    @Test
    fun `reponse rapide et correcte doit donner 100 points`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 3000)
        assertThat(points).isEqualTo(100)
    }

    @Test
    fun `reponse moyenne et correcte doit donner 75 points`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 7000)
        assertThat(points).isEqualTo(75)
    }

    @Test
    fun `reponse lente et correcte doit donner 50 points`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 12000)
        assertThat(points).isEqualTo(50)
    }

    @Test
    fun `reponse incorrecte doit donner -10 points`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = false, tempsReponseMs = 3000)
        assertThat(points).isEqualTo(-10)
    }

    @Test
    fun `temps de reponse 0ms doit donner 100 points si correct`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 0)
        assertThat(points).isEqualTo(100)
    }

    @Test
    fun `temps de reponse exactement 5s doit donner 75 points`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 5000)
        assertThat(points).isEqualTo(75)
    }

    @Test
    fun `temps de reponse exactement 10s doit donner 50 points`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 10000)
        assertThat(points).isEqualTo(50)
    }

    @Test
    fun `temps de reponse juste avant 5s doit donner 100 points`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 4999)
        assertThat(points).isEqualTo(100)
    }

    @Test
    fun `temps de reponse juste avant 10s doit donner 75 points`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 9999)
        assertThat(points).isEqualTo(75)
    }

    @Test
    fun `temps de reponse negatif doit donner 100 points si correct`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = -100)
        assertThat(points).isEqualTo(100)
    }

    @Test
    fun `temps de reponse tres long doit donner 50 points si correct`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 999999)
        assertThat(points).isEqualTo(50)
    }
}
