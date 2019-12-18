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
    private val CLIENT_ID_ANDROID_1: String = "237114950965-7rkhg22vtsrku0b56vp969q8dlss210h.apps.googleusercontent.com"
    private val CLIENT_ID_ANDROID_2: String = "237114950965-niu6sehjl26jd5ble3rqiqvumnjhcs83.apps.googleusercontent.com"
    private val CLIENT_ID_ANDROID_3: String = "237114950965-te4prvsa0k3pitsibchse5hjf6gpjgvo.apps.googleusercontent.com"
    private val CLIENT_ID_ANDROID_4: String = "237114950965-4e43k64d6qhb7645p7jvufri6ic0hgh0.apps.googleusercontent.com"
    private val jacksonFactory = JacksonFactory()

    fun getMail(idToken: String): String {
        val verifier = GoogleIdTokenVerifier.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            jacksonFactory
        ).setAudience(listOf(
            CLIENT_ID,
            CLIENT_ID_iOS,
            CLIENT_ID_ANDROID_1,
            CLIENT_ID_ANDROID_2,
            CLIENT_ID_ANDROID_3,
            CLIENT_ID_ANDROID_4
        )).build()

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