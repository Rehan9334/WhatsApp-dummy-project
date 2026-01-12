package com.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.entity.ChatMessageEntity;
import com.chat.service.MessageStatusService;

@RestController
@RequestMapping("/api/messages")
public class MessageStatusController {

	private final MessageStatusService messageStatusService;

    public MessageStatusController(MessageStatusService messageStatusService) {
        this.messageStatusService = messageStatusService;
    }

    // receiver calls this when they open chat/read messages
    @PatchMapping("/{id}/read")
    public ResponseEntity<ChatMessageEntity> markAsRead(@PathVariable String id) {
        ChatMessageEntity entity = messageStatusService.markAsRead(id);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatMessageEntity> getById(@PathVariable String id) {
        ChatMessageEntity entity = messageStatusService.getMessage(id);
        return ResponseEntity.ok(entity);
    }
	
	
}
