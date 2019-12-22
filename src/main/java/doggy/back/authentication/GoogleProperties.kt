package doggy.back.authentication

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties(prefix = "google")
class GoogleProperties {

    lateinit var clientId: String
    lateinit var clientIdIos: String
    lateinit var clientIdAndroid1: String
    lateinit var clientIdAndroid2: String
    lateinit var clientIdAndroid3: String
    lateinit var clientIdAndroid4: String
}