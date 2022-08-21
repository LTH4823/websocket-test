package org.taerock.websockettest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.taerock.websockettest.dto.ResponseMessage;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService notificationService;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate, NotificationService notificationService) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyFront(final String message){

        ResponseMessage responseMessage = new ResponseMessage(message);
        notificationService.sendGlobalNotification();

        messagingTemplate.convertAndSend("/topic/messages",responseMessage);

    }

    public void notifyUser(final String id, final String message){

        ResponseMessage responseMessage = new ResponseMessage(message);
        notificationService.sendPrivateGlobalNotification(id);

        messagingTemplate.convertAndSendToUser(id,"/topic/private-messages",responseMessage);

    }


}
