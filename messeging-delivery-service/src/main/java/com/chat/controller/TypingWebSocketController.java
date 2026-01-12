package com.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.chat.dto.TypingEvent;

@Controller
public class TypingWebSocketController {

	
	private final SimpMessagingTemplate messagingTemplate;

    public TypingWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/typing")
    public void handleTyping(TypingEvent event) {
        String destination = "/topic/typing." + event.getToUser();
        messagingTemplate.convertAndSend(destination, event);
    }
}
