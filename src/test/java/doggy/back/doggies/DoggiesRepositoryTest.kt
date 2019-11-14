package doggy.back.doggies

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DoggiesRepositoryTest {

    @InjectMocks private lateinit var repository: DoggiesRepository


    @Ignore
    @Test
    fun `JAF should not be in the doggy skool`() {
        assertThat(repository.getDoggies().map { it.trigramme }).doesNotContain("JAF")
    }

}