package doggy.back.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @Autowired
    private lateinit var googleRepository: GoogleRepository

    @GetMapping("/auth/{id}")
    fun getDoggies(
        @PathVariable("id") id: String
    ): String {
        return googleRepository.getPrenom(id);
    }

}