package org.taerock.websockettest.handler;

import com.sun.security.auth.UserPrincipal;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Log4j2
public class UserHandshakeHandler  extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        final String randomId = UUID.randomUUID().toString();

        log.info("User with ID '{}' opened the page", randomId);

        return new UserPrincipal(randomId);
    }
}
