package doggy.back.authentication

import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationHandler(private val googleRepository: GoogleRepository) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("id_token")
        if (token == null) {
            throw PasAthentifieException()
        } else {
            try {
                googleRepository.getMail(token)
            } catch (e: Throwable) {
                throw PasAthentifieException()
            }
        }
        return super.preHandle(request, response, handler)
    }
}