package com.chat.controller;


import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.dto.ChatMessageRequest;
import com.chat.dto.ChatMessageResponse;
import com.chat.service.ChatKafkaProducer;

@RestController
@RequestMapping("/api/messages")
public class ChatController {

	private final ChatKafkaProducer chatKafkaProducer;

    public ChatController(ChatKafkaProducer chatKafkaProducer) {
        this.chatKafkaProducer = chatKafkaProducer;
    }
    
    @PostMapping
    public ResponseEntity<ChatMessageResponse> sendMessage(@RequestBody ChatMessageRequest request) {

        String messageId = chatKafkaProducer.sendMessage(request);

        ChatMessageResponse response = new ChatMessageResponse(
                messageId,
                request.getFromUser(),
                request.getToUser(),
                request.getContent(),
                Instant.now(),
                "SENT"   // when API call succeeds
        );
    	return ResponseEntity.ok(response);
    }
    
    
}
