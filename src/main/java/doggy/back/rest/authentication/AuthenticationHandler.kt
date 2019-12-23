package doggy.back.rest.authentication

import doggy.back.authentication.GoogleAuthRepository
import doggy.back.domain.doggy.DoggyPersistance
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationHandler(
    private val googleAuthRepository: GoogleAuthRepository,
    private val doggyPersistance: DoggyPersistance
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("id_token")
        if (token == null) {
            throw PasAuthentifieException()
        } else {
            val mail = googleAuthRepository.getMail(token)
            doggyPersistance.recupererToutLesDoggies().find { it.email == mail } ?: throw TexistePasException(mail)
        }
        return super.preHandle(request, response, handler)
    }
}