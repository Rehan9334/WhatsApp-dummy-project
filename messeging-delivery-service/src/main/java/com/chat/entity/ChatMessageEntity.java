package com.chat.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="chat_messages")
public class ChatMessageEntity {

	@Id
    private String id;

    @Column(nullable = false)
    private String fromUser;

    @Column(nullable = false)
    private String toUser;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private Instant sentAt;

    @Column(nullable = false)
    private String status; // SENT, DELIVERED, READ

    public ChatMessageEntity() {
    }

    public ChatMessageEntity(String id, String fromUser, String toUser, String content, Instant sentAt, String status) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.content = content;
        this.sentAt = sentAt;
        this.status = status;
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Instant getSentAt() {
        return sentAt;
    }

    public void setSentAt(Instant sentAt) {
        this.sentAt = sentAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
	
}
