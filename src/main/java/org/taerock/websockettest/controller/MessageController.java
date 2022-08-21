package org.taerock.websockettest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import org.taerock.websockettest.dto.Message;
import org.taerock.websockettest.dto.ResponseMessage;
import org.taerock.websockettest.service.NotificationService;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private NotificationService notificationService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) throws InterruptedException{

        Thread.sleep(1000);
        notificationService.sendGlobalNotification();
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));

    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(final Message message, final Principal principal) throws InterruptedException{

        Thread.sleep(1000);
        notificationService.sendPrivateGlobalNotification(principal.getName());
        return new ResponseMessage(HtmlUtils.htmlEscape("Sending private message to user"+principal.getName()+": "+message.getMessageContent()));

    }


}
