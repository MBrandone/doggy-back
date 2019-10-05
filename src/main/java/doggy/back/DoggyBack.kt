package doggy.back

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DoggyBack

fun main(args: Array<String>) {
    SpringApplication.run(DoggyBack::class.java, *args)
}