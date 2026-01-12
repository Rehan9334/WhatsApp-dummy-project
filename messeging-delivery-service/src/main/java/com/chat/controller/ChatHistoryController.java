package com.chat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.entity.ChatMessageEntity;
import com.chat.repository.ChatMessageRepository;

@RestController
@RequestMapping("/api/chats")
public class ChatHistoryController {

	private final ChatMessageRepository repository;

    public ChatHistoryController(ChatMessageRepository repository) {
        this.repository = repository;
    }

    // Get full conversation between two users
    @GetMapping("/{user1}/{user2}")
    public List<ChatMessageEntity> getConversation(@PathVariable String user1, @PathVariable String user2) {
        return repository.findByFromUserAndToUserOrFromUserAndToUserOrderBySentAtAsc(
                user1, user2, user2, user1
        );
    }
	
	
}
