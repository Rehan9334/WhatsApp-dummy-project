package com.chat.service;

import java.io.IOException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.chat.entity.ChatMessageEntity;
import com.chat.repository.ChatMessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChatMessageConsumer {

	
	private final ChatMessageRepository repository;
    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    public ChatMessageConsumer(ChatMessageRepository repository,
                               SimpMessagingTemplate messagingTemplate) {
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String messageJson) {
        try {
            ChatMessageWrapper wrapper = objectMapper.readValue(messageJson, ChatMessageWrapper.class);

            ChatMessageEntity entity = new ChatMessageEntity(
                    wrapper.getMessageId(),
                    wrapper.getFromUser(),
                    wrapper.getToUser(),
                    wrapper.getContent(),
                    Instant.ofEpochMilli(wrapper.getSentAtEpochMillis()),
                    "DELIVERED"
            );

            repository.save(entity);

            // 🔔 Push real-time message to receiver
            String destination = "/topic/messages." + wrapper.getToUser();
            messagingTemplate.convertAndSend(destination, entity);

            System.out.println("Saved & pushed message: " + entity.getId() + " to " + destination);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // same as before
    public static class ChatMessageWrapper {
        private String messageId;
        private String fromUser;
        private String toUser;
        private String content;
        private long sentAtEpochMillis;

        public ChatMessageWrapper() {
        }

        // getters & setters...
        public String getMessageId() { return messageId; }
        public void setMessageId(String messageId) { this.messageId = messageId; }

        public String getFromUser() { return fromUser; }
        public void setFromUser(String fromUser) { this.fromUser = fromUser; }

        public String getToUser() { return toUser; }
        public void setToUser(String toUser) { this.toUser = toUser; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }

        public long getSentAtEpochMillis() { return sentAtEpochMillis; }
        public void setSentAtEpochMillis(long sentAtEpochMillis) { this.sentAtEpochMillis = sentAtEpochMillis; }
    }
	
}
