package org.taerock.websockettest.handler;

import com.sun.security.auth.UserPrincipal;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

//기본 핸드쉐이크 설정
@Log4j2
public class UserHandshakeHandler extends DefaultHandshakeHandler {

    //해당 유저의 메시지 전송 설정
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        //유저 메세지 전송시 UUID 설정
        final String randomId = UUID.randomUUID().toString();

        log.info("User with ID '{}' opened the page", randomId);

        return new UserPrincipal(randomId);
    }
}
