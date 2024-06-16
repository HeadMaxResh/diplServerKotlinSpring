package com.diplback.diplserver

import com.diplback.diplserver.interseptor.MyInterceptor
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry
            .addInterceptor(myInterceptor())
            .addPathPatterns("/**")
    }

    @Bean
    fun myInterceptor(): MyInterceptor = MyInterceptor()
}


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topics")
        config.setApplicationDestinationPrefixes("/app")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/my-ws").setAllowedOrigins("*").withSockJS()
    }
}

@Configuration
@ConfigurationProperties(prefix = "services")
class ServiceConfig {

    var questions: Boolean = true
    var tasks: Boolean = true
    var events: Boolean = true
    var map: Boolean = true
}
