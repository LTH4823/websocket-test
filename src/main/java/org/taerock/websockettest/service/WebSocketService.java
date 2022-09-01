package org.taerock.websockettest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.taerock.websockettest.dto.ResponseMessage;

//웹소켓 채팅 서비스
@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;
    //비밀글 서비스 선언
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
        notificationService.sendPrivateNotification(id);

        messagingTemplate.convertAndSendToUser(id,"/topic/private-messages",responseMessage);

    }


}
