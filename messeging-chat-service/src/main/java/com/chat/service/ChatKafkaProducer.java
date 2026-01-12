package com.chat.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.chat.dto.ChatMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChatKafkaProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    
    @Value("${app.kafka.topic}")
    private String topic;

	public ChatKafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		super();
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
		
	}
    
	public String sendMessage(ChatMessageRequest request) {
        String messageId = UUID.randomUUID().toString();

        try {
            // Wrap with ID before sending
            ChatMessageWrapper wrapper = new ChatMessageWrapper(
                    messageId,
                    request.getFromUser(),
                    request.getToUser(),
                    request.getContent(),
                    System.currentTimeMillis()
            );

            String payload = objectMapper.writeValueAsString(wrapper);
            kafkaTemplate.send(topic, wrapper.getToUser(), payload); // key: toUser (for partitioning)
            return messageId;

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing chat message", e);
        }
    }

    // Inner static class = what we really send to Kafka
    public static class ChatMessageWrapper {
        private String messageId;
        private String fromUser;
        private String toUser;
        private String content;
        private long sentAtEpochMillis;

        public ChatMessageWrapper() {
        }

        public ChatMessageWrapper(String messageId, String fromUser, String toUser, String content, long sentAtEpochMillis) {
            this.messageId = messageId;
            this.fromUser = fromUser;
            this.toUser = toUser;
            this.content = content;
            this.sentAtEpochMillis = sentAtEpochMillis;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getFromUser() {
            return fromUser;
        }

        public void setFromUser(String fromUser) {
            this.fromUser = fromUser;
        }

        public String getToUser() {
            return toUser;
        }

        public void setToUser(String toUser) {
            this.toUser = toUser;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getSentAtEpochMillis() {
            return sentAtEpochMillis;
        }

        public void setSentAtEpochMillis(long sentAtEpochMillis) {
            this.sentAtEpochMillis = sentAtEpochMillis;
        }
    }
}
