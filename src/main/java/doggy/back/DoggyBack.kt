package doggy.back

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DoggyBack {

    @Bean
    fun objectMapper() = jacksonObjectMapper()
}

fun main(args: Array<String>) {
    SpringApplication.run(DoggyBack::class.java, *args)
}