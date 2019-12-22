package doggy.back;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import doggy.back.authentication.GoogleAuthProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GoogleConfig (
    private val googleAuthProperties: GoogleAuthProperties
){

    @Bean
    fun googleIdTokenVerifier(): GoogleIdTokenVerifier {
        return GoogleIdTokenVerifier.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
             JacksonFactory()
        ).setAudience(listOf(
            googleAuthProperties.clientId,
            googleAuthProperties.clientIdIos,
            googleAuthProperties.clientIdAndroid1,
            googleAuthProperties.clientIdAndroid2,
            googleAuthProperties.clientIdAndroid3,
            googleAuthProperties.clientIdAndroid4
        )).build()
    }
}
