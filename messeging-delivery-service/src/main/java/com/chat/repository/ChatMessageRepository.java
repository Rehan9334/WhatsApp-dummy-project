package com.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.entity.ChatMessageEntity;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, String>{

	
	// Get conversation between two users (like WhatsApp chat)
    List<ChatMessageEntity> findByFromUserAndToUserOrderBySentAtAsc(String fromUser, String toUser);

    List<ChatMessageEntity> findByFromUserAndToUserOrFromUserAndToUserOrderBySentAtAsc(
            String from1, String to1, String from2, String to2
    );
	
	
}
