package com.chat.service;

import org.springframework.stereotype.Service;

import com.chat.entity.ChatMessageEntity;
import com.chat.repository.ChatMessageRepository;

import jakarta.transaction.Transactional;

@Service
public class MessageStatusService {

	private final ChatMessageRepository repository;

    public MessageStatusService(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ChatMessageEntity markAsRead(String messageId) {
        return repository.findById(messageId)
                .map(entity -> {
                    entity.setStatus("READ");
                    return repository.save(entity);
                })
                .orElseThrow(() -> new RuntimeException("Message not found: " + messageId));
    }

    public ChatMessageEntity getMessage(String messageId) {
        return repository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found: " + messageId));
    }
	
	
}
