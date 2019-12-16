package doggy.back.authentication

import com.google.api.client.auth.openidconnect.IdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GoogleRepository {

    private val LOGGER: Logger = LoggerFactory.getLogger(GoogleRepository::class.java)

    private val CLIENT_ID: String = "237114950965-te4prvsa0k3pitsibchse5hjf6gpjgvo.apps.googleusercontent.com"
    private val CLIENT_ID_iOS: String = "237114950965-4e43k64d6qhb7645p7jvufri6ic0hgh0.apps.googleusercontent.com"
    private val jacksonFactory = JacksonFactory()

    fun getMail(idToken: String): String {
        val verifier = GoogleIdTokenVerifier.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            jacksonFactory
        ).setAudience(listOf(CLIENT_ID, CLIENT_ID_iOS)).build()

        try {
            val verify = verifier.verify(idToken)
            if (verify != null) {
                val payload: IdToken.Payload = verify.payload
                return payload.get("email").toString()
            }
        } catch (e: Exception) {
            LOGGER.error("Erreur google", e)
            return "Erreur d'authent"
        }

        return "pas authentifié"
    }
}