package doggy.back.domain.partie

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculateurScoreTest {

    private val calculateurScore = CalculateurScore()

    @Test
    fun `devrait retourner 100 points pour une reponse rapide et correcte`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 3000L)
        assertThat(points).isEqualTo(100)
    }

    @Test
    fun `devrait retourner 75 points pour une reponse moyenne et correcte`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 7000L)
        assertThat(points).isEqualTo(75)
    }

    @Test
    fun `devrait retourner 50 points pour une reponse lente et correcte`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 12000L)
        assertThat(points).isEqualTo(50)
    }

    @Test
    fun `devrait retourner -10 points pour une mauvaise reponse`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = false, tempsReponseMs = 3000L)
        assertThat(points).isEqualTo(-10)
    }

    @Test
    fun `devrait retourner 100 points pour 0 ms (reponse immediate)`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 0L)
        assertThat(points).isEqualTo(100)
    }

    @Test
    fun `devrait retourner 100 points pour exactement 4999 ms (limite rapide)`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 4999L)
        assertThat(points).isEqualTo(100)
    }

    @Test
    fun `devrait retourner 75 points pour exactement 5000 ms (limite moyenne inferieure)`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 5000L)
        assertThat(points).isEqualTo(75)
    }

    @Test
    fun `devrait retourner 75 points pour exactement 9999 ms (limite moyenne superieure)`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 9999L)
        assertThat(points).isEqualTo(75)
    }

    @Test
    fun `devrait retourner 50 points pour exactement 10000 ms (limite lente)`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = true, tempsReponseMs = 10000L)
        assertThat(points).isEqualTo(50)
    }

    @Test
    fun `devrait retourner -10 points pour mauvaise reponse avec temps negatif`() {
        val points = calculateurScore.calculerPoints(reponseCorrecte = false, tempsReponseMs = -1000L)
        assertThat(points).isEqualTo(-10)
    }
}
