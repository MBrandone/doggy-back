package doggy.back

import doggy.back.rest.authentication.AuthenticationHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val authenticationHandler: AuthenticationHandler) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        //registry.addInterceptor(authenticationHandler)
        super.addInterceptors(registry)
    }
}