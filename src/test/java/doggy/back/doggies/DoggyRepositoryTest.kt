package doggy.back.doggies

import doggy.back.infra.doggies.DoggyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@RunWith(MockitoJUnitRunner::class)
class DoggyRepositoryTest {

    @InjectMocks
    private lateinit var repository: DoggyRepository

    @Mock
    private lateinit var jdbcTemplate: JdbcTemplate;
    @Mock
    private lateinit var nameJdbcTemplate: NamedParameterJdbcTemplate

    @Test
    fun `JAF ne devrait pas etre a la doggy skool`() {
        assertThat(repository.getDoggies().map { it.trigramme }).doesNotContain("JAF")
    }

}