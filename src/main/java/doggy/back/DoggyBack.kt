package doggy.back

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject


@SpringBootApplication
class DoggyBack {
    @Bean
    fun objectMapper() = jacksonObjectMapper()

    @Bean
    fun restTemplate():RestTemplate {
        return RestTemplateBuilder().build()
    }

    @Scheduled(fixedDelay=600000)
    fun pingFront(restTemplate: RestTemplate) {
        restTemplate.getForObject<String>("https://doggy-skool-app.herokuapp.com/")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(DoggyBack::class.java, *args)
}
