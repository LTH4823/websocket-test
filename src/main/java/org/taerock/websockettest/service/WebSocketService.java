package org.taerock.websockettest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.taerock.websockettest.dto.ResponseMessage;

@Service
public class WebSocketService {

    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyFront(final String message){

        ResponseMessage responseMessage = new ResponseMessage(message);

        messagingTemplate.convertAndSend("/topic/messages",responseMessage);

    }

    public void notifyUser(final String id, final String message){

        ResponseMessage responseMessage = new ResponseMessage(message);

        messagingTemplate.convertAndSendToUser(id,"/topic/private-messages",responseMessage);

    }


}
