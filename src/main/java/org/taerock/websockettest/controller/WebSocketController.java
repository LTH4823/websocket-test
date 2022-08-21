package org.taerock.websockettest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.taerock.websockettest.dto.Message;
import org.taerock.websockettest.service.WebSocketService;

@RestController
public class WebSocketController {

    @Autowired
    WebSocketService webSocketService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message){
        webSocketService.notifyFront(message.getMessageContent());
    }

}
