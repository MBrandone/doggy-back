package doggy.back.doggies

import doggy.back.infra.doggies.DoggyDataRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DoggyRepositoryTest {

    @InjectMocks
    private lateinit var repository: DoggyDataRepository


    @Test
    @Ignore
    fun `JAF ne devrait pas etre a la doggy skool`() {
        assertThat(repository.findAll().map { it.trigramme }).doesNotContain("JAF")
    }

}