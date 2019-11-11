package doggy.back

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.flywaydb.core.Flyway
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DoggyBack {

    @Bean
    fun objectMapper() = jacksonObjectMapper()

    @Bean
    fun flyway(): Flyway {
        return Flyway.configure()
            .baselineOnMigrate(true)
            .load()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(DoggyBack::class.java, *args)
}
