package doggy.back.authentication

import doggy.back.doggies.DoggiesRepository
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationHandler(
    private val googleAuthRepository: GoogleAuthRepository,
    private val doggiesRepository: DoggiesRepository
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("id_token")
        if (token == null) {
            throw PasAuthentifieException()
        } else {
            val mail = googleAuthRepository.getMail(token)
            doggiesRepository.getDoggies().find { it.email == mail } ?: throw TexistePasException(mail)
        }
        return super.preHandle(request, response, handler)
    }
}