package org.taerock.websockettest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.taerock.websockettest.dto.ResponseMessage;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;


    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalNotification(){

        ResponseMessage message = new ResponseMessage("Global Notification");

        messagingTemplate.convertAndSend("/topic/global-notification", message);

    }

    public void sendPrivateGlobalNotification(final String userId){

        ResponseMessage message = new ResponseMessage("Private Notification");

        messagingTemplate.convertAndSendToUser(userId,"/topic/global-notification", message);

    }

}
