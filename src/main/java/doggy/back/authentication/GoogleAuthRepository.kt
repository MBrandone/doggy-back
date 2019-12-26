package doggy.back.authentication

import com.google.api.client.auth.openidconnect.IdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import doggy.back.rest.authentication.PasAuthentifieException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GoogleAuthRepository (
    private val verifier: GoogleIdTokenVerifier
){

    private val LOGGER: Logger = LoggerFactory.getLogger(GoogleAuthRepository::class.java)

    fun getMail(idToken: String): String {
        try {
            val verify = verifier.verify(idToken)
            if (verify != null) {
                val payload: IdToken.Payload = verify.payload
                return payload.get("email").toString()
            }
        } catch (e: Exception) {
            LOGGER.error("Erreur google", e)
        }
        throw PasAuthentifieException()
    }
}