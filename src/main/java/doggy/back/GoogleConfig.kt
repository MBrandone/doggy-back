package doggy.back;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import doggy.back.authentication.GoogleProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GoogleConfig (
    private val googleProperties: GoogleProperties
){

    @Bean
    fun googleIdTokenVerifier(): GoogleIdTokenVerifier {
        return GoogleIdTokenVerifier.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
             JacksonFactory()
        ).setAudience(listOf(
            googleProperties.clientId,
            googleProperties.clientIdIos,
            googleProperties.clientIdAndroid1,
            googleProperties.clientIdAndroid2,
            googleProperties.clientIdAndroid3,
            googleProperties.clientIdAndroid4
        )).build()
    }
}
