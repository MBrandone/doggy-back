package doggy.back

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate

@EnableScheduling
@SpringBootApplication
class DoggyBack {

    @Bean
    fun objectMapper() = jacksonObjectMapper()

    @Bean
    fun restTemplate() = RestTemplate()
}

fun main(args: Array<String>) {
    SpringApplication.run(DoggyBack::class.java, *args)
}
