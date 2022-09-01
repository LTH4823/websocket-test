package org.taerock.websockettest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.taerock.websockettest.handler.UserHandshakeHandler;

//STOMP 프로토콜 웹소켓 설정
@Configuration
//웹소켓 메세지 브로커 선언
@EnableWebSocketMessageBroker
//웹소켓 메세지 브로커 설정을 위한 상속
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {

    //전송되는 메세지 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/ws");
    }

    //연결되는 메세지 전달 설정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/our-websocket")
                .setHandshakeHandler(new UserHandshakeHandler())
                .withSockJS();
    }
}
